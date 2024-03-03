import mongoose, { Schema, Document } from "mongoose";
import IUsuario from "../interfaces/usuario";

const SchemaUsuario: Schema = new Schema<IUsuario>(
    {
      nombre: { type: Schema.Types.String, required: true },
      apellidos: { type: Schema.Types.String, required: true },
      correo: { type: Schema.Types.String, required: true },
      contra: { type: Schema.Types.String, required: true },
      instrumentos: {
        type: [Schema.Types.ObjectId],
        ref: "instrumentos",
      },
    },
    { timestamps: false, versionKey: false }
  );
  
  export const UsuarioModel = mongoose.model<IUsuario & Document>(
    "usuarios",
    SchemaUsuario
  );