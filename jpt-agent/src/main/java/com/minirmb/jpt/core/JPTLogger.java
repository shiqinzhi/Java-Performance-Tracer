package com.minirmb.jpt.core;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 
 * 使用这个类的原因是java.util.logging.Logger没有行数。
 * 
 * 日志会写到 $user_home/.jpt 目录下.
 *
 * 不记录要发送到远程的数据。
 * 
 * @author Spooner
 *
 */
public class JPTLogger {
	private static Logger logger;
	private final static int logSize = 10 * 1024 * 1024;
	private final static SimpleDateFormat DateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");

	private static Logger getLogger() {
		if (null == logger) {
			logger = Logger.getLogger("jpt");
			logger.setLevel(Level.ALL);
			logger.setUseParentHandlers(false);

			// create dirs
			String logPath = System.getProperties().getProperty("user.home") + File.separator + ".jpt" + File.separator
					+ "log";
			File logPathDir = new File(logPath);
			if (!logPathDir.exists()) {
				logPathDir.mkdirs();
			}

			// init log
			String logFile = logPath + File.separator + "jpt.log";
			try {
				Formatter logFormatter = new Formatter() {
					@Override
					public synchronized String format(LogRecord record) {
						return "\n" + record.getMessage();
					}
				};

				FileHandler fileHandler = new FileHandler(logFile, logSize, 9, true);
				fileHandler.setLevel(Level.ALL);
				fileHandler.setFormatter(logFormatter);
				logger.addHandler(fileHandler);

				ConsoleHandler consoleHandler = new ConsoleHandler();
				consoleHandler.setLevel(Level.ALL);
				consoleHandler.setFormatter(logFormatter);
				logger.addHandler(consoleHandler);
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
				throw new IllegalArgumentException("Can not init log!!!");
			}

			logger.info(DateFormat.format(new Date()) + " log inited. log file : " + logFile);
		}
		return logger;
	}

	public static void log(Object... objs) {
		StringBuilder builder = new StringBuilder();
		builder.append(DateFormat.format(new Date()) + " ");
		builder.append(Thread.currentThread().getStackTrace()[2] + " ");
		for (Object o : objs) {
			builder.append(String.valueOf(o));
			builder.append(" ");
		}
		JPTLogger.getLogger().info(builder.append("\n").toString());
	}

	public static <E extends Throwable> String ExceptionStackTraceToString(E t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw, true));
		return sw.getBuffer().toString();
	}
}
