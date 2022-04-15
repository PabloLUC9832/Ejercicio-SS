package mx.uv.t4is.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.todolist.AgregarTareaRequest;
import https.t4is_uv_mx.todolist.AgregarTareaResponse;
import https.t4is_uv_mx.todolist.EliminarTareaRequest;
import https.t4is_uv_mx.todolist.EliminarTareaResponse;
import https.t4is_uv_mx.todolist.ModificarTareaRequest;
import https.t4is_uv_mx.todolist.ModificarTareaResponse;
import https.t4is_uv_mx.todolist.MostrarTareasResponse;

@Endpoint
public class ToDoListEndPoint {
    
    @Autowired
    Itareas itareas;
    //AGREGAR TAREA
    @PayloadRoot(localPart = "AgregarTareaRequest", namespace = "https://t4is.uv.mx/todolist")
    @ResponsePayload
    public AgregarTareaResponse agregarTarea(@RequestPayload AgregarTareaRequest peticion){
        AgregarTareaResponse respuesta = new AgregarTareaResponse();
        respuesta.setRespuesta(peticion.getNombre());
        Tareas tarea = new Tareas();
        tarea.setNombre(peticion.getNombre());
        tarea.setDescripcion(peticion.getDescripcion());
        tarea.setDescripcion(peticion.getImportancia());
        tarea.setStatus(peticion.getStatus());
        itareas.save(tarea);
        return respuesta;
    }

    //MOSTRAR TAREA
    @PayloadRoot(localPart = "MostrarTareasRequest" ,namespace = "https://t4is.uv.mx/todolist")
    @ResponsePayload
    public MostrarTareasResponse MostrarTareas(){
        MostrarTareasResponse respuesta = new MostrarTareasResponse();
        Iterable<Tareas> lista = itareas.findAll();
        
        for (Tareas tarea : lista) {
            MostrarTareasResponse.Tareas e = new MostrarTareasResponse.Tareas();
            e.setNombre(tarea.getNombre());
            e.setId(tarea.getId());
            e.setId(tarea.getStatus());
            respuesta.getTareas().add(e);
        }
        return respuesta;
    }
    
    //MODIFICAR TAREA
    @PayloadRoot(localPart = "ModificarTareaRequest" ,namespace = "https://t4is.uv.mx/todolist")
    @ResponsePayload
    public ModificarTareaResponse modificarSaludo(@RequestPayload ModificarTareaRequest peticion){
        ModificarTareaResponse respuesta = new ModificarTareaResponse(); 
        Tareas tarea = new Tareas();
        tarea.setId(peticion.getId());
        tarea.setNombre(peticion.getNombre());
        tarea.setDescripcion(peticion.getDescripcion());
        tarea.setDescripcion(peticion.setImportancia());
        tarea.setStatus(peticion.getStatus());
        itareas.save(tarea);
        respuesta.setRespuesta("Tarea modificada");
        return respuesta;
    }
    //ELIMINAR TAREA
    @PayloadRoot(localPart = "EliminarTareaRequest", namespace = "https://t4is.uv.mx/todolist")
    @ResponsePayload
    public EliminarTareaResponse eliminarTarea(@RequestPayload EliminarTareaRequest peticion){
        EliminarTareaResponse respuesta = new EliminarTareaResponse();
        itareas.deleteById(peticion.getId());
        respuesta.setRespuesta("Tarea eliminado");
        return respuesta;
    }

}
