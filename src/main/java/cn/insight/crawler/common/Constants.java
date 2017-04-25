package cn.insight.crawler.common;

/**
 * Created by fengwei on 17/4/25.
 */
public interface Constants {

    /**
     * 页面类型
     */
    public enum PageType {
        LIST("list"), DETAIL("detail");

        String type;

        private PageType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return this.type;
        }
    }

}
