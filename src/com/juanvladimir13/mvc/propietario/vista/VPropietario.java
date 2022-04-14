package com.juanvladimir13.mvc.propietario.vista;

import com.juanvladimir13.template.MaryTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class VPropietario {
  private String title="Gestionar propietario";

  private String findAndDelete(String id) {
    String td = "<a href='/propietario/" + id + "'>&nbsp;&#x1f50e;</a>";
    td += "<a href='/propietario/" + id + "/delete'> &#x274c;&nbsp;</a>";
    return td;
  }
  
  private String generateTable(List<Map<String, String>> rows){
    String table = "";
    for (int index = 0; index < rows.size(); index++) {
      Map<String, String> row = rows.get(index);
      table += "<tr>";
      table += "<td>" + row.get("nombres") + "</td>";
      table += "<td>" + row.get("apellidos") + "</td>";
      table += "<td>" + row.get("ci") + "</td>";
      table += "<td>" + findAndDelete(row.get("id")) + "</td>";
      table += "</tr>";
    }
    return !table.isEmpty() ? table : "Sin datos";
  }

  public String inflaterTable(List<Map<String, String>> rows) {
    Map<String, String> data = new HashMap<>();
    data.put("tbody", generateTable(rows));
    data.put("title", title);
    return MaryTemplate.render("propietario", data);
  }
  
  public String inflaterFind(List<Map<String, String>> table, Map<String, String> row){
    Map<String, String> data = new HashMap<>();
    data.put("title", title);
    data.put("tbody", generateTable(table));
    
    if (!row.isEmpty()){
      data.put("id", row.getOrDefault("id","0"));
      data.put("nombres", row.getOrDefault("nombres",""));
      data.put("apellidos", row.getOrDefault("apellidos",""));
      data.put("ci", row.getOrDefault("ci",""));
    }
    return MaryTemplate.render("propietario", data);
  }
}
