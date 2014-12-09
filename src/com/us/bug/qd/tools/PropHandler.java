package com.us.bug.qd.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropHandler
{
	@SuppressWarnings("unused")
	private static final String BUG_PRIORITY = "bugPriority.properties";

	@SuppressWarnings("unused")
	private static final String BUG_REASON_TYPE = "BugReasonType.properties";

	@SuppressWarnings("unused")
	private static final String BUG_STATE = "bugState.properties";

	@SuppressWarnings("unused")
	private static final String TASK_REASON_TYPE = "TaskReasonType.properties";
	
	private static final String BUG_SEVERITY = "bugSeverity.properties";

	public Properties getPropFile(String path)
	{
		Properties config = new Properties();
		InputStream in = PropHandler.class.getClassLoader()
				.getResourceAsStream(path);
		config = new Properties();
		try
		{
			config.load(in);
		}
		catch (IOException e)
		{
			System.out.println("No " + path + ".properties defined error");
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return config;
	}

	// 读取properties的全部信息
	@SuppressWarnings("unchecked")
	public List<String> readAllProperties(String path)
	{
		Properties config = getPropFile(path);
		List<String> prop = new ArrayList<String>();
		try
		{
			Iterator<Object> en = (Iterator<Object>) config.propertyNames();
			while (en.hasNext())
			{
				String key = (String) en.next();
				String value = config.getProperty(key);
				prop.add(value);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ConfigInfoError" + e.toString());
		}
		return prop;
	}

	// 根据key读取value
	public String readValue(String path, String key)
	{
		Properties config = getPropFile(path);
		try
		{
			String value = config.getProperty(key);
			return value;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("ConfigInfoError" + e.toString());
			return null;
		}
	}
	
	
	
	
	
	public List<String> getBugSeverity()
	{
		return readAllProperties(PropHandler.BUG_SEVERITY);
	}
}
