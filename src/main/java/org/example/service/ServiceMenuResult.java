package org.example.service;

import java.util.List;

public class ServiceMenuResult {
    public String result_text;
    public String title_list;
    public List<String> list;

    public ServiceMenuResult(String result_text, String title_list, List<String> list) {
        this.result_text = result_text;
        this.title_list = title_list;
        this.list = list;
    }
}
