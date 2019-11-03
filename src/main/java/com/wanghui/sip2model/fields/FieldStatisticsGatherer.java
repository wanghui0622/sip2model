package com.wanghui.sip2model.fields;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.NavigableSet;
import java.util.TreeMap;

/**
 * @auther wanghui
 * @create 2018-11-13 0:36
 * @Description 字段静态生成器
 */
public class FieldStatisticsGatherer extends Thread {
    private static FieldStatisticsGatherer fieldStatisticsGatherer = null;
    private TreeMap<String, Usage> usages = new TreeMap<String, Usage>();

    public static FieldStatisticsGatherer getFieldStatisticsGatherer() {
        if (FieldStatisticsGatherer.fieldStatisticsGatherer == null) {
            FieldStatisticsGatherer.fieldStatisticsGatherer = new FieldStatisticsGatherer();
        }
        return FieldStatisticsGatherer.fieldStatisticsGatherer;
    }

    /**
     * todo 记录已经使用过的构造器，提高效率。
     * @param name
     * @param required
     */
    public void recordUsage(String name, boolean required) {
        this.usages.put(name, new Usage(this.usages.get(name), required));
    }

    public void printUsageStatistics() {
        System.out.println("Usage stats");
        NavigableSet<String> names = this.usages.navigableKeySet();
        String name = names.first();
        while (name != null) {
            Usage usage = this.usages.get(name);
            System.out.println(name + ": " + usage.uses + " " + (usage.mutable ? "Mutable " : "") + (usage.required ? "Required " : "")
                    + (usage.optional ? "Optional" : ""));
            name = names.higher(name);
        }
    }

    class Usage {
        public boolean required;
        public boolean optional;
        public boolean mutable;
        public int uses = 0;

        protected Usage() {
        }

        protected Usage(Usage u, String name, boolean mutable) {
            this.mutable = mutable;
            if (u != null) {
                this.required = u.required;
                this.optional = u.optional;
                this.uses = u.uses;
            }
        }

        protected Usage(Usage u, boolean required) {
            if (u != null) {
                this.required = required ? true : u.required;
                this.optional = (!required) ? true : u.optional;
                this.uses = u.uses + 1;
                this.mutable = u.mutable;
            } else {
                this.required = required;
                this.optional = !required;
                this.uses = 1;
            }
        }
    }

    @Override
    public void run() {
        FieldStatisticsGatherer.getFieldStatisticsGatherer().printUsageStatistics();
    }

    public void loadFieldDefinitions(Hashtable<String, FieldDefinition> fields) {
        Enumeration<String> names = fields.keys();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            this.usages.put(name, new Usage(this.usages.get(name), name, ((fields.get(name)).policy == null)));
        }
    }
}
