package ar.edu.utn.dds.k3003.Controller;

import ar.edu.utn.dds.k3003.app.Fachada;
import ar.edu.utn.dds.k3003.model.Heladera;
import ar.edu.utn.dds.k3003.model.Subscriptor.*;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import org.jetbrains.annotations.NotNull;


public class SubscriptoresController implements Handler {
    private final Fachada fachada;

    public SubscriptoresController(Fachada fachada) {
        super();
        this.fachada = fachada;
    }

    @Override
    public void handle(@NotNull Context ctx) {

    }

    public void suscribirViandasDisponibles (Context ctx){
        try {
            SubscriptorDto subscriptorDTO = ctx.bodyAsClass(SubscriptorDto.class);
            Heladera heladera = fachada.getRepoHeladera().findById(subscriptorDTO.getIdHeladera());
            SubscriptorViandasDisponibles sub = new SubscriptorViandasDisponibles(subscriptorDTO.getIdColaborador(),subscriptorDTO.getNviandas());
            sub.suscribirse(heladera);
            fachada.getRepoHeladera().persistirSubscriptor(sub);
            fachada.getRepoHeladera().actualizar(heladera);
            ctx.result("Suscrito correctamente");
            ctx.status(200);
        }
        catch (Exception e){
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.result("Error de solicitud");
        }
    }
    public void getSubscriptores(Context ctx){
         ctx.json(fachada.getRepoHeladera().findAllSubscriptores());
    }
    public void suscribirViandasFaltantes (Context ctx){
        try {
            SubscriptorDto subscriptorDTO = ctx.bodyAsClass(SubscriptorDto.class);
            Heladera heladera = fachada.getRepoHeladera().findById(subscriptorDTO.getIdHeladera());
            SubscriptorViandasFaltantes sub = new SubscriptorViandasFaltantes(subscriptorDTO.getIdColaborador(),subscriptorDTO.getNviandas());
            sub.suscribirse(heladera);
            fachada.getRepoHeladera().persistirSubscriptor(sub);
            fachada.getRepoHeladera().actualizar(heladera);
            ctx.result("Suscrito correctamente");
            ctx.status(200);
        }
        catch (Exception e){
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.result("Error de solicitud");
        }
    }
    public void suscribirDesperfecto (Context ctx){
        try {
            SubscriptorDesperfectoDTO subDTO = ctx.bodyAsClass(SubscriptorDesperfectoDTO.class);
            Heladera heladera = fachada.getRepoHeladera().findById(subDTO.getIdHeladera());
            SubscriptorDesperfecto sub = new SubscriptorDesperfecto(subDTO.getIdColaborador());
            sub.suscribirse(heladera);
            fachada.getRepoHeladera().persistirSubscriptor(sub);
            fachada.getRepoHeladera().actualizar(heladera);
            ctx.result("Suscrito correctamente");
            ctx.status(200);
        }
        catch (Exception e){
            ctx.status(HttpStatus.BAD_REQUEST);
            ctx.result("Error de solicitud");
        }
    }


}
