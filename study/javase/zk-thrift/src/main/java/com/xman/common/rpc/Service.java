//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.apache.commons.lang.StringUtils;

public class Service {
    public static final String PROVIDER_CATEGORY = "providers";
    public static final String CONSUMER_CATEGORY = "consumers";
    public String host;
    public int port;
    public String name;
    public String category;
    private Map<String, String> parameters;
    private String string;

    public Service(String host, int port, String name, String category) {
        this(host, port, name, category, (Map)null);
    }

    public Service(String host, int port, String name, String category, String... pairs) {
        this(host, port, name, category, toStringMap(pairs));
    }

    public Service(String host, int port, String name, String category, Map<String, String> parameters) {
        if (host != null && name != null && category != null && port > 0 && port <= 65535) {
            this.host = host;
            this.port = port;
            this.name = name;
            this.category = category;
            if (parameters != null) {
                this.parameters = new HashMap(parameters);
            }

        } else {
            throw new IllegalArgumentException("invalid args");
        }
    }

    public static Service valueOf(String service) {
        service = decode(service);
        String[] tmp = StringUtils.split(service, ':');
        if (tmp.length == 4) {
            return new Service(tmp[0], Integer.parseInt(tmp[1]), tmp[2], tmp[3]);
        } else {
            return tmp.length == 5 ? new Service(tmp[0], Integer.parseInt(tmp[1]), tmp[2], tmp[3], kvToStringMap(tmp[4])) : null;
        }
    }

    public String getKey() {
        return this.host + ":" + this.port;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public String getParameterAndDecoded(String key) {
        return this.getParameterAndDecoded(key, (String)null);
    }

    public String getParameterAndDecoded(String key, String defaultValue) {
        return decode(this.getParameter(key, defaultValue));
    }

    public String getParameter(String key) {
        return (String)this.parameters.get(key);
    }

    public String getParameter(String key, String defaultValue) {
        String value = this.getParameter(key);
        return value != null && value.length() != 0 ? value : defaultValue;
    }

    public double getParameter(String key, double defaultValue) {
        String value = this.getParameter(key);
        if (value != null && value.length() != 0) {
            double d = Double.parseDouble(value);
            return d;
        } else {
            return defaultValue;
        }
    }

    public long getParameter(String key, long defaultValue) {
        String value = this.getParameter(key);
        if (value != null && value.length() != 0) {
            long l = Long.parseLong(value);
            return l;
        } else {
            return defaultValue;
        }
    }

    public int getParameter(String key, int defaultValue) {
        String value = this.getParameter(key);
        if (value != null && value.length() != 0) {
            int i = Integer.parseInt(value);
            return i;
        } else {
            return defaultValue;
        }
    }

    public static Map<String, String> kvToStringMap(String kvs) {
        Map<String, String> parameters = new HashMap();
        String[] fields = StringUtils.split(kvs, '&');

        for(int i = 0; i < fields.length; ++i) {
            String[] kv = StringUtils.split(fields[i], '=');
            parameters.put(kv[0], kv[1]);
        }

        return parameters;
    }

    public static Map<String, String> toStringMap(String... pairs) {
        Map<String, String> parameters = new HashMap();
        if (pairs.length > 0) {
            if (pairs.length % 2 != 0) {
                throw new IllegalArgumentException("pairs must be even.");
            }

            for(int i = 0; i < pairs.length; i += 2) {
                parameters.put(pairs[i], pairs[i + 1]);
            }
        }

        return parameters;
    }

    public static String encode(String value) {
        if (value != null && value.length() != 0) {
            try {
                return URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                throw new RuntimeException(var2.getMessage(), var2);
            }
        } else {
            return "";
        }
    }

    public static String decode(String value) {
        if (value != null && value.length() != 0) {
            try {
                return URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException var2) {
                throw new RuntimeException(var2.getMessage(), var2);
            }
        } else {
            return "";
        }
    }

    private void buildParameters(StringBuilder buf) {
        if (this.getParameters() != null && this.getParameters().size() > 0) {
            Iterator i$ = (new TreeMap(this.getParameters())).entrySet().iterator();

            while(i$.hasNext()) {
                Entry<String, String> entry = (Entry)i$.next();
                if (entry.getKey() != null && ((String)entry.getKey()).length() > 0) {
                    buf.append((String)entry.getKey());
                    buf.append("=");
                    buf.append(entry.getValue() == null ? "" : ((String)entry.getValue()).trim());
                    buf.append('&');
                }
            }
        }

    }

    public String toString() {
        if (this.string != null) {
            return this.string;
        } else {
            StringBuilder buf = new StringBuilder();
            buf.append(this.host);
            buf.append(':');
            buf.append(this.port);
            buf.append(':');
            buf.append(this.name);
            buf.append(':');
            buf.append(this.category);
            buf.append(':');
            this.buildParameters(buf);
            buf.setLength(buf.length() - 1);
            this.string = buf.toString();
            return this.string;
        }
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        result = 31 * result + (string != null ? string.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Service other = (Service)obj;
            if (this.host == null) {
                if (other.host != null) {
                    return false;
                }
            } else if (!this.host.equals(other.host)) {
                return false;
            }

            if (this.port != other.port) {
                return false;
            } else {
                if (this.name == null) {
                    if (other.name != null) {
                        return false;
                    }
                } else if (!this.name.equals(other.name)) {
                    return false;
                }

                if (this.category == null) {
                    if (other.category != null) {
                        return false;
                    }
                } else if (!this.category.equals(other.category)) {
                    return false;
                }

                if (this.parameters == null) {
                    if (other.parameters != null) {
                        return false;
                    }
                } else if (!this.parameters.equals(other.parameters)) {
                    return false;
                }

                return true;
            }
        }
    }
}
