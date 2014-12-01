package com.library.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.LevelFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.FixedWindowRollingPolicy;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.FilterReply;

public class LogUtil {
	/**
	 * 
	 * �� �� ��������־�ļ�,���������������ļ������󣬵��ԣ���Ϣ��
	 * ÿ���ļ���ౣ��������ʷ��¼
	 * �������� : 2013-11-19
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * 
	 */
	public static void configureLogbackDirectory(String path) {
		// reset the default context (which may already have been initialized)
		// since we want to reconfigure it
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// lc.reset();

		// ���DEBUG�����ļ�
		PatternLayoutEncoder encoder_debug = new PatternLayoutEncoder();
		encoder_debug.setContext(lc);
		encoder_debug.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n");
		encoder_debug.start();

		LevelFilter filter_debug = new LevelFilter();
		filter_debug.setLevel(Level.DEBUG);
		filter_debug.setOnMatch(FilterReply.ACCEPT);
		filter_debug.setOnMismatch(FilterReply.DENY);
		filter_debug.start();

		RollingFileAppender<ILoggingEvent> fileAppender_debug = new RollingFileAppender<ILoggingEvent>();
		// fileAppender_debug.setLazy(true);
		fileAppender_debug.setContext(lc);
		// fileAppender_debug.setFile(logFile + "/debug.log"); // If rollingPolicy.fileNamePattern already set, you don't need this.
		fileAppender_debug.setEncoder(encoder_debug);
		fileAppender_debug.addFilter(filter_debug);

		// ���ʱ�����ƹ���
		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy_debug = new TimeBasedRollingPolicy<ILoggingEvent>();
		rollingPolicy_debug.setFileNamePattern(path + "/debug_%d.log");
		rollingPolicy_debug.setMaxHistory(7); // ����������־��¼
		rollingPolicy_debug.setParent(fileAppender_debug);  // parent and context required!
		rollingPolicy_debug.setContext(lc);
		rollingPolicy_debug.start();

		fileAppender_debug.setRollingPolicy(rollingPolicy_debug);
		fileAppender_debug.start();
		// /////////////////////////////����

		// ���INFO�����ļ�
		PatternLayoutEncoder encoder_info = new PatternLayoutEncoder();
		encoder_info.setContext(lc);
		encoder_info.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n");
		encoder_info.start();

		LevelFilter filter_info = new LevelFilter();
		filter_info.setLevel(Level.INFO);
		filter_info.setOnMatch(FilterReply.ACCEPT);
		filter_info.setOnMismatch(FilterReply.DENY);
		filter_info.start();

		RollingFileAppender<ILoggingEvent> fileAppender_info = new RollingFileAppender<ILoggingEvent>();
		// fileAppender_info.setLazy(true);
		fileAppender_info.setContext(lc);
		// fileAppender_info.setFile(logFile + "/info.log");
		fileAppender_info.setEncoder(encoder_info);
		fileAppender_info.addFilter(filter_info);

		// ���ʱ�����ƹ���
		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy_info = new TimeBasedRollingPolicy<ILoggingEvent>();
		rollingPolicy_info.setFileNamePattern(path + "/info_%d.log");
		rollingPolicy_info.setMaxHistory(7); // ����������־��¼
		rollingPolicy_info.setParent(fileAppender_info);  // parent and context required!
		rollingPolicy_info.setContext(lc);
		rollingPolicy_info.start();

		fileAppender_info.setRollingPolicy(rollingPolicy_info);
		fileAppender_info.start();
		// /////////////////////////////����

		// ���ERROR�����ļ�
		PatternLayoutEncoder encoder_error = new PatternLayoutEncoder();
		encoder_error.setContext(lc);
		encoder_error.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n");
		encoder_error.start();

		LevelFilter filter_error = new LevelFilter();
		filter_error.setLevel(Level.ERROR);
		filter_error.setOnMatch(FilterReply.ACCEPT);
		filter_error.setOnMismatch(FilterReply.DENY);
		filter_error.start();

		RollingFileAppender<ILoggingEvent> fileAppender_error = new RollingFileAppender<ILoggingEvent>();
		// fileAppender_error.setLazy(true);
		fileAppender_error.setContext(lc);
		// fileAppender_error.setFile(logFile + "/error.log");
		fileAppender_error.setEncoder(encoder_error);
		fileAppender_error.addFilter(filter_error);

		// ���ʱ�����ƹ���
		TimeBasedRollingPolicy<ILoggingEvent> rollingPolicy_error = new TimeBasedRollingPolicy<ILoggingEvent>();
		rollingPolicy_error.setFileNamePattern(path + "/error_%d.log");
		rollingPolicy_error.setMaxHistory(7); // ����������־��¼
		rollingPolicy_error.setParent(fileAppender_error);  // parent and context required!
		rollingPolicy_error.setContext(lc);
		rollingPolicy_error.start();

		fileAppender_error.setRollingPolicy(rollingPolicy_error);
		fileAppender_error.start();
		// ////////////////////////////////////////����

		// ��� Logcat���
		PatternLayoutEncoder encoder_logcat = new PatternLayoutEncoder();
		encoder_logcat.setContext(lc);
		encoder_logcat.setPattern("[%thread] %msg%n");
		encoder_logcat.start();

		LogcatAppender logcatAppender = new LogcatAppender();
		logcatAppender.setContext(lc);
		logcatAppender.setEncoder(encoder_logcat);
		logcatAppender.start();
		// /////////////////////////����

		// add the newly created appenders to the root logger;
		// qualify Logger to disambiguate from org.slf4j.Logger
		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		// root.setLevel(Level.INFO); //�����������
		root.addAppender(fileAppender_error);
		root.addAppender(fileAppender_info);
		root.addAppender(fileAppender_debug);
		root.addAppender(logcatAppender);
	}

	/**
	 * 
	 * �� �� �����õ�һ��־�ļ�
	 * �������� : 2014-1-5
	 * �� �� �� lx
	 * �޸����� :
	 * �� �� �� ��
	 * 
	 * @version : 1.0
	 * @param path
	 * 
	 */
	public static void configureOnlyLogFile(String path) {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		// lc.reset();

		PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(lc);
		encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n");
		encoder.start();

		SizeBasedTriggeringPolicy<ILoggingEvent> sizeRolling = new SizeBasedTriggeringPolicy<ILoggingEvent>();
		sizeRolling.setMaxFileSize("10MB");
		sizeRolling.setContext(lc);
		sizeRolling.start();

		RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<ILoggingEvent>();
		// fileAppender_debug.setLazy(true);
		fileAppender.setContext(lc);
		fileAppender.setFile(path + "/app.log");
		fileAppender.setEncoder(encoder);
		fileAppender.setTriggeringPolicy(sizeRolling);

		FixedWindowRollingPolicy wRollingPolicy = new FixedWindowRollingPolicy();
		wRollingPolicy.setMinIndex(1);
		wRollingPolicy.setMaxIndex(3);
		wRollingPolicy.setFileNamePattern("%i.log.zip");
		wRollingPolicy.setContext(lc);
		wRollingPolicy.setParent(fileAppender);
		wRollingPolicy.start();

		fileAppender.setRollingPolicy(wRollingPolicy);
		fileAppender.start();
		// /////////////////////////////����

		// ��� Logcat���
		PatternLayoutEncoder encoder_logcat = new PatternLayoutEncoder();
		encoder_logcat.setContext(lc);
		encoder_logcat.setPattern("[%msg%n");
		encoder_logcat.start();

		LogcatAppender logcatAppender = new LogcatAppender();
		logcatAppender.setContext(lc);
		logcatAppender.setEncoder(encoder_logcat);
		logcatAppender.start();
		// /////////////////////////����

		ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.addAppender(fileAppender);
		root.addAppender(logcatAppender);
	}
}
