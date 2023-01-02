package com.nev.cg.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MysqlInfo {
    private String sqlHost;
    private String sqlPort;
    private String dbName;
    private String sqlUname;
    private String pwd;
    private String driver;


}
