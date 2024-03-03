import express, { Application } from "express";
import mongoose from "mongoose";
import cors from "cors";
import rutasUsuarios from "../routes/usuario";
import rutasInstrumento from "../routes/instrumento";


class Servidor {
  
  private app: Application;
  private port: string;
  private rutasApi = {
    instrumentos: "/api/instrumentos/",
    usuarios: "/api/usuarios/",
  };

  constructor() {
    this.app = express();
    this.port = "3000";
    this.establecerConexionBD();
    this.middlewares();
    this.routes();
  }

  async establecerConexionBD() {
    mongoose
      .connect(process.env.MONGO_URL!)
      .then(() => {
        console.log("Éxito al conectar a Mongo");
      })
      .catch((error) => {
        console.log("Error al conectar a Mongo" + error);
      });

    mongoose.set("debug", true);
  }

  middlewares() {
    //CORS
    this.app.use(cors());
    //Parseo del body
    this.app.use(express.json());
    //Codificar url
    this.app.use(express.urlencoded({ extended: true }));
  }

  routes() {
    this.app.use(this.rutasApi.usuarios, rutasUsuarios);
    this.app.use(this.rutasApi.instrumentos, rutasInstrumento);
  }

  listen() {
    this.app.listen(this.port, () => {
      console.log("El servidor activo en puerto " + this.port);
    });
  }
}

//Exportamos la clase
export default Servidor;
