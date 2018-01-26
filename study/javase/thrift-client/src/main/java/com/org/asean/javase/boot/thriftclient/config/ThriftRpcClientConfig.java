package com.org.asean.javase.boot.thriftclient.config;

public class ThriftRpcClientConfig {

	private boolean registeFlag;
	private boolean tframedFlag;
	private boolean multiFlag;
	private String serviceClazz;
	private String serverAddr;

	public boolean getRegisteFlag() {
		return registeFlag;
	}

	public boolean isMultiFlag() {
		return multiFlag;
	}

	public void setMultiFlag(boolean multiFlag) {
		this.multiFlag = multiFlag;
	}

	public void setRegisteFlag(boolean registeFlag) {
		this.registeFlag = registeFlag;
	}

	public boolean getTframedFlag() {
		return tframedFlag;
	}

	public void setTframedFlag(boolean tframedFlag) {
		this.tframedFlag = tframedFlag;
	}

	public String getServiceClazz() {
		return serviceClazz;
	}

	public void setServiceClazz(String serviceClazz) {
		this.serviceClazz = serviceClazz;
	}

	public String getServerAddr() {
		return serverAddr;
	}

	public void setServerAddr(String serverAddr) {
		this.serverAddr = serverAddr;
	}

}
