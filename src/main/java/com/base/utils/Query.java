package com.base.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    /**
     * 每页条数
     */
    private int pageSize = 5;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        String stringPageIndex = "pageIndex";
        String stringPageSize = "pageSize";

        // 分页参数
        if (params.get(stringPageIndex) != null) {
            pageIndex = Integer.parseInt((String) params.get(stringPageIndex));
        }
        if (params.get(stringPageSize) != null) {
            pageSize = Integer.parseInt((String) params.get(stringPageSize));
        }

        this.put("offset", (pageIndex - 1) * pageSize);
        this.put("pageIndex", pageIndex);
        this.put("pageSize", pageSize);

        // mybatis-plus分页
        this.page = new Page<>(pageIndex, pageSize);

    }

    public Page<T> getPage() {
        return page;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }
}
