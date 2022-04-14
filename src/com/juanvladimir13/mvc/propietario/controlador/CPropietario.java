package com.juanvladimir13.mvc.propietario.controlador;

import com.juanvladimir13.httpserver.Response;
import com.juanvladimir13.mvc.propietario.modelo.MPropietario;
import com.juanvladimir13.mvc.propietario.vista.VPropietario;
import java.util.List;
import java.util.Map;

/**
 *
 * @author juanvladimir13<juanvladimir13@gmail.com>
 * @see https://github.com/juanvladimir13
 */
public class CPropietario {

  private MPropietario model;
  private VPropietario view;

  public CPropietario() {
    this.model = new MPropietario();
    this.view = view = new VPropietario();
  }

  public Response list() {
    List<Map<String, String>> data = model.list();
    String html = view.inflaterTable(data);
    return new Response().setResponseHTML(html);
  }

  public Response find(String id) {
    model.setId(id);
    Map<String, String> data = model.find();
    List<Map<String, String>> table = model.list();

    String html = view.inflaterFind(table, data);
    return new Response().setResponseHTML(html);
  }

  public Response delete(String id) {
    model.setId(id);
    model.delete();
    return new Response().setResponseRedirect("/propietario");
  }

  public Response save(Map<String, String> data) {
    model.setData(data);
    if (!model.save()) {
      List<Map<String, String>> table = model.list();
      String html = view.inflaterFind(table, data);
      return new Response().setResponseHTML(html);
    }

    String id = data.getOrDefault("id", "0");
    if (!id.equals("0")) {
      return new Response().setResponseRedirect("/propietario/" + id);
    }
    
    Map<String,String> row = model.find("ci", data.getOrDefault("ci", ""));
    List<Map<String, String>> table = model.list();
    String html = view.inflaterFind(table, row);
    return new Response().setResponseHTML(html);
  }
}
