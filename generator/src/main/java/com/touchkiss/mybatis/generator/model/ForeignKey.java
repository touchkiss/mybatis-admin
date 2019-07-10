package com.touchkiss.mybatis.generator.model;

/**
 * @Author Touchkiss
 * @date 2018/12/21
 */

import org.apache.commons.lang3.StringUtils;

public class ForeignKey {
    private String fktableName;
    private String fkcolumnName;
    private String pktableName;
    private String pkcolumnName;
    private Column pkColumn;
    private String foreignType;
    private String foreignTypeImport;

    public String getForeignTypeFirstLower() {
        if (StringUtils.isNotBlank(this.foreignType)) {
            if (this.foreignType.length() == 1) {
                return this.foreignType.substring(0, 1).toLowerCase();
            }

            if (this.foreignType.length() > 1) {
                return this.foreignType.substring(0, 1).toLowerCase() + this.foreignType.substring(1);
            }
        }

        return this.foreignType;
    }

    public ForeignKey() {
    }

    public String getFktableName() {
        return this.fktableName;
    }

    public String getFkcolumnName() {
        return this.fkcolumnName;
    }

    public String getPktableName() {
        return this.pktableName;
    }

    public String getPkcolumnName() {
        return this.pkcolumnName;
    }

    public Column getPkColumn() {
        return this.pkColumn;
    }

    public String getForeignType() {
        return this.foreignType;
    }

    public String getForeignTypeImport() {
        return this.foreignTypeImport;
    }

    public void setFktableName(String fktableName) {
        this.fktableName = fktableName;
    }

    public void setFkcolumnName(String fkcolumnName) {
        this.fkcolumnName = fkcolumnName;
    }

    public void setPktableName(String pktableName) {
        this.pktableName = pktableName;
    }

    public void setPkcolumnName(String pkcolumnName) {
        this.pkcolumnName = pkcolumnName;
    }

    public void setPkColumn(Column pkColumn) {
        this.pkColumn = pkColumn;
    }

    public void setForeignType(String foreignType) {
        this.foreignType = foreignType;
    }

    public void setForeignTypeImport(String foreignTypeImport) {
        this.foreignTypeImport = foreignTypeImport;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof ForeignKey)) {
            return false;
        } else {
            ForeignKey other = (ForeignKey)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$fktableName = this.getFktableName();
                    Object other$fktableName = other.getFktableName();
                    if (this$fktableName == null) {
                        if (other$fktableName == null) {
                            break label95;
                        }
                    } else if (this$fktableName.equals(other$fktableName)) {
                        break label95;
                    }

                    return false;
                }

                Object this$fkcolumnName = this.getFkcolumnName();
                Object other$fkcolumnName = other.getFkcolumnName();
                if (this$fkcolumnName == null) {
                    if (other$fkcolumnName != null) {
                        return false;
                    }
                } else if (!this$fkcolumnName.equals(other$fkcolumnName)) {
                    return false;
                }

                Object this$pktableName = this.getPktableName();
                Object other$pktableName = other.getPktableName();
                if (this$pktableName == null) {
                    if (other$pktableName != null) {
                        return false;
                    }
                } else if (!this$pktableName.equals(other$pktableName)) {
                    return false;
                }

                label74: {
                    Object this$pkcolumnName = this.getPkcolumnName();
                    Object other$pkcolumnName = other.getPkcolumnName();
                    if (this$pkcolumnName == null) {
                        if (other$pkcolumnName == null) {
                            break label74;
                        }
                    } else if (this$pkcolumnName.equals(other$pkcolumnName)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$pkColumn = this.getPkColumn();
                    Object other$pkColumn = other.getPkColumn();
                    if (this$pkColumn == null) {
                        if (other$pkColumn == null) {
                            break label67;
                        }
                    } else if (this$pkColumn.equals(other$pkColumn)) {
                        break label67;
                    }

                    return false;
                }

                Object this$foreignType = this.getForeignType();
                Object other$foreignType = other.getForeignType();
                if (this$foreignType == null) {
                    if (other$foreignType != null) {
                        return false;
                    }
                } else if (!this$foreignType.equals(other$foreignType)) {
                    return false;
                }

                Object this$foreignTypeImport = this.getForeignTypeImport();
                Object other$foreignTypeImport = other.getForeignTypeImport();
                if (this$foreignTypeImport == null) {
                    if (other$foreignTypeImport != null) {
                        return false;
                    }
                } else if (!this$foreignTypeImport.equals(other$foreignTypeImport)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ForeignKey;
    }

    @Override
    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $fktableName = this.getFktableName();
        result = result * 59 + ($fktableName == null ? 43 : $fktableName.hashCode());
        Object $fkcolumnName = this.getFkcolumnName();
        result = result * 59 + ($fkcolumnName == null ? 43 : $fkcolumnName.hashCode());
        Object $pktableName = this.getPktableName();
        result = result * 59 + ($pktableName == null ? 43 : $pktableName.hashCode());
        Object $pkcolumnName = this.getPkcolumnName();
        result = result * 59 + ($pkcolumnName == null ? 43 : $pkcolumnName.hashCode());
        Object $pkColumn = this.getPkColumn();
        result = result * 59 + ($pkColumn == null ? 43 : $pkColumn.hashCode());
        Object $foreignType = this.getForeignType();
        result = result * 59 + ($foreignType == null ? 43 : $foreignType.hashCode());
        Object $foreignTypeImport = this.getForeignTypeImport();
        result = result * 59 + ($foreignTypeImport == null ? 43 : $foreignTypeImport.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ForeignKey(fktableName=" + this.getFktableName() + ", fkcolumnName=" + this.getFkcolumnName() + ", pktableName=" + this.getPktableName() + ", pkcolumnName=" + this.getPkcolumnName() + ", pkColumn=" + this.getPkColumn() + ", foreignType=" + this.getForeignType() + ", foreignTypeImport=" + this.getForeignTypeImport() + ")";
    }
}
