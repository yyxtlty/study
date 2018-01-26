package com.org.asean.javase.boot.thriftregist.util;

public class ThriftRpcServiceConfig {

	private Boolean registeFlag;
	private String serviceClazz;
	private Object serviceImpl;
	

	public boolean getRegisteFlag() {
		return registeFlag;
	}

	public void setRegisteFlag(boolean registeFlag) {
		this.registeFlag = registeFlag;
	}

	public String getServiceClazz() {
		return serviceClazz;
	}

	public void setServiceClazz(String serviceClazz) {
		this.serviceClazz = serviceClazz;
	}

	/**
	 * @return the serviceImpl
	 */
	public Object getServiceImpl() {
		return serviceImpl;
	}

	/**
	 * @param serviceImpl the serviceImpl to set
	 */
	public void setServiceImpl(Object serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

}
