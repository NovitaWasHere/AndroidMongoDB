import dotenv from "dotenv";
import Servidor from "./models/server";
//Config dotenv
dotenv.config();

//Creamows nuestro servidor
const server = new Servidor();

//Al encender la app, iniciamos el servidor
server.listen();
