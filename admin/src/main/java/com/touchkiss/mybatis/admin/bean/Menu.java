package com.touchkiss.mybatis.admin.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 14:46
 */
@Data
public class Menu {
    private List<MenuGroup> menuGroups;

    @Data
    public static class MenuGroup {
        private String showName;
        private String name;
        private String iconClass;
        List<MenuItem> menuItems;

        @Data
        public static class MenuItem {
            private String name;
            private String showName;
            private String iconClass;
            private String url;
        }
    }
}
