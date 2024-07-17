package com.minirmb.jpt.core;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

public class RoundAdviceAdapter extends AdviceAdapter {
	private final String className;
	private final String methodName;
	private final String descriptor;
	private final Label startFinally = new Label();
	private final String methodSign;
	private final String metricClassName;
	private final String tracerId;

	public RoundAdviceAdapter(String tracerId, MethodVisitor mv, int access, String className, String methodName,
			String descriptor) {
		super(ASM9, mv, access, methodName, descriptor);
		this.className = className.replace("/", ".");
		this.methodName = methodName;
		this.descriptor = descriptor;
		this.methodSign = className.replace("/", ".") + "." + methodName + descriptor;
		this.metricClassName = "com/minirmb/jpt/collect/Collector";
		this.tracerId = tracerId;
	}

	/**
	 * 在 visitCode 方法中，必须先调用 super.visitCode()，因为这会通知 ASM 开始处理新的方法代码块。
	 * 如果你在调用 super.visitCode() 之前插入自定义逻辑，ASM 还没有准备好接受这些指令。
	 */
	@Override
	public void visitCode() {
		super.visitCode();
		mv.visitLabel(startFinally);
	}

	@Override
	public void onMethodEnter() {
		mv.visitLdcInsn(tracerId);
		mv.visitLdcInsn(className);
		mv.visitLdcInsn(methodName + descriptor);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
		mv.visitMethodInsn(INVOKESTATIC, metricClassName, "RecordMethodIn",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", false);
	}

	@Override
	public void onMethodExit(int opcode) {
		if (opcode != ATHROW) {
			onFinally(opcode);
		}
	}

	private void onFinally(int opcode) {
		mv.visitLdcInsn(tracerId);
		mv.visitLdcInsn(className);
		mv.visitLdcInsn(methodName + descriptor);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false);
		mv.visitMethodInsn(INVOKESTATIC, metricClassName, "RecordMethodOut",
				"(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", false);
	}

	/**
	 * 在 visitMaxs 方法中，mv.visitMaxs(maxStack, maxLocals) 必须在最后调用。这是因为在调用 visitMaxs 之前，你需要完成所有的字节码指令，
	 * 包括异常处理块的定义和 finally 块的逻辑插入。visitMaxs 通知 ASM 框架方法的最大堆栈大小和本地变量数目，并且这个信息只有在所有指令都插入完成之后才能确定。
	 * @param maxStack maximum stack size of the method.
	 * @param maxLocals maximum number of local variables for the method.
	 */
	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		Label endFinally = new Label();
		mv.visitTryCatchBlock(startFinally, endFinally, endFinally, null);
		mv.visitLabel(endFinally);
		onFinally(ATHROW);
		mv.visitInsn(ATHROW);
		//用于设置方法的最大堆栈大小和本地变量数目。这个方法必须在方法的结尾调用，以便通知 ASM 框架该方法的最大堆栈大小和本地变量数目是多少。
		mv.visitMaxs(maxStack, maxLocals);
	}
}
