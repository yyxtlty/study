//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.xman.common.rpc;

import com.google.common.collect.Lists;
import com.xman.common.rpc.filter.ConsumerContextFilter;
import com.xman.common.rpc.filter.Filter;
import com.xman.common.rpc.filter.ProviderContextFilter;
import com.xman.common.rpc.filter.trace.TraceFilter;
import com.xman.common.rpc.invoke.Invocation;
import com.xman.common.rpc.invoke.Invoker;
import java.util.List;

public class FilterProvider {
    public FilterProvider() {
    }

    public static List<Filter> configConsumerFilters(ThriftConfig config) {
        List<Filter> consumerFilters = Lists.newArrayList();
        consumerFilters.add(new ConsumerContextFilter());
        if (config.traceConfig.isEnable()) {
            consumerFilters.add(new TraceFilter(config.traceConfig, true));
        }

        return consumerFilters;
    }

    public static List<Filter> configProviderFilters(ThriftConfig config) {
        List<Filter> providerFilters = Lists.newArrayList();
        providerFilters.add(new ProviderContextFilter());
        if (config.traceConfig.isEnable()) {
            providerFilters.add(new TraceFilter(config.traceConfig, false));
        }

        return providerFilters;
    }

    public static Invoker buildInvokerChain(List<Filter> filters, Invoker invoker) {
        final Invoker last = invoker;

        for(int i = filters.size() - 1; i >= 0; --i) {
            final Filter filter = (Filter)filters.get(i);
             new Invoker() {
                public String toString() {
                    return filter.toString();
                }

                public Object invoke(Invocation invocation) throws Throwable {
                    return filter.invoke(last, invocation);
                }
            };
        }

        return last;
    }
}
