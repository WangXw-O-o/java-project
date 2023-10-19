package com.wxw.code.sync.common;

public class LineBuilder {

    public static final String enter = "\n";
    public static final String tab = "\t";

    private final StringBuilder sb = new StringBuilder();

    private int nextLinePrefixTabCount = 0;

    public LineBuilder append(String line) {
        if (line.endsWith("{")) {
            if (line.startsWith("}")) {
                nextLinePrefixTabCount--;
            }
            sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
            nextLinePrefixTabCount++;
            return this;
        }
        if (line.endsWith("}") || line.startsWith("}")){
            nextLinePrefixTabCount--;
            sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
            return this;
        }
        sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
        return this;
    }

    public LineBuilder appendTag(String line) {
        if (line.startsWith("<") && !line.startsWith("</")) {
            sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
            nextLinePrefixTabCount++;
            return this;
        }
        if (line.startsWith("</")) {
            nextLinePrefixTabCount --;
            sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
            return this;
        }
        sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
        return this;
    }

    public LineBuilder appendTagWithoutTabCheck(String line) {
        sb.append(tab.repeat(Math.max(0, nextLinePrefixTabCount))).append(line).append(enter);
        return this;
    }

    public LineBuilder appendTag(String line, int tabCount) {
        sb.append(tab.repeat(Math.max(0, tabCount))).append(line).append(enter);
        return this;
    }


    public String toString() {
        return sb.toString();
    }

    public int getNextLinePrefixTabCount() {
        return nextLinePrefixTabCount;
    }

    public void setNextLinePrefixTabCount(int nextLinePrefixTabCount) {
        this.nextLinePrefixTabCount = nextLinePrefixTabCount;
    }
}
