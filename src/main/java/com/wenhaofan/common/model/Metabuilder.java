package com.wenhaofan.common.model;

import javax.sql.DataSource;

import com.jfinal.plugin.activerecord.generator.MetaBuilder;

public class Metabuilder extends MetaBuilder {

	public Metabuilder(DataSource dataSource) {
		super(dataSource);
		// TODO Auto-generated constructor stub
	}
	 @Override
	    protected boolean isSkipTable(String tableName) {
	        return !tableName.startsWith("html");
	    }
}
