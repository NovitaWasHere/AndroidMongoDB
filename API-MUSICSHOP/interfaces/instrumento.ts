import { Document, Schema } from "mongoose";

export default interface IInstrumento extends Document {
  nombre: Schema.Types.String;
  descripcion: Schema.Types.String;
  disponibilidad: Schema.Types.Boolean;
  precio: Schema.Types.Number;
}
