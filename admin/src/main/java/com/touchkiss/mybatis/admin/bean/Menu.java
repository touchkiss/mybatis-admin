package com.touchkiss.mybatis.admin.bean;

import java.util.List;

/**
 * @Author Touchkiss
 * @create: 2019-06-25 14:46
 */
public class Menu {
    private List<MenuGroup> menuGroups;

    public List<MenuGroup> getMenuGroups() {
        return menuGroups;
    }

    public void setMenuGroups(List<MenuGroup> menuGroups) {
        this.menuGroups = menuGroups;
    }

    public static class MenuGroup {
        private String showName;
        private String name;
        private String iconClass;
        List<MenuItem> menuItems;

        public String getShowName() {
            return showName;
        }

        public void setShowName(String showName) {
            this.showName = showName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconClass() {
            return iconClass;
        }

        public void setIconClass(String iconClass) {
            this.iconClass = iconClass;
        }

        public List<MenuItem> getMenuItems() {
            return menuItems;
        }

        public void setMenuItems(List<MenuItem> menuItems) {
            this.menuItems = menuItems;
        }

        public static class MenuItem {
            private String name;
            private String showName;
            private String iconClass;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShowName() {
                return showName;
            }

            public void setShowName(String showName) {
                this.showName = showName;
            }

            public String getIconClass() {
                return iconClass;
            }

            public void setIconClass(String iconClass) {
                this.iconClass = iconClass;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
