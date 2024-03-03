import mongoose, { Schema, Document } from "mongoose";
import IInstrumento from "../interfaces/instrumento";

const SchemaInstrumento: Schema = new Schema<IInstrumento>(
  {
    nombre: { type: Schema.Types.String, required: true },
    descripcion: { type: Schema.Types.String, required: true },
    disponibilidad: { type: Schema.Types.Boolean,required: true },
    precio: {type: Schema.Types.Number ,required: true},
   
  },
  { timestamps: false, versionKey: false }
);

export const InstrumentoModel = mongoose.model<IInstrumento & Document>(
  "instrumentos",
  SchemaInstrumento
);