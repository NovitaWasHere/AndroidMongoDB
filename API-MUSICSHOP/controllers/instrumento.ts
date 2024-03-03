import { Request, Response } from "express";
import mongoose from "mongoose";
import { InstrumentoModel } from "../models/instrumento";


const getInstrumentos = async (req: Request, res: Response) => {
  await InstrumentoModel.find()
  .exec()
  .then((resultados) => {
    return res.status(200).json({
      exito: true,
      datos: resultados,
    });
  })
  .catch((error) => {
    return res.status(500).json({
      exito: false,
      error,
    });
  });
};


const getInstrumento = async (req: Request, res: Response) => {
  const { id } = req.params;

  await InstrumentoModel.findById(id)
    .exec()
    .then((resultado) => {
      return res.status(200).json({
        exito: true,
        datos: resultado,
      });
    })
    .catch((error) => {
      return res.status(500).json({
        exito: false,
        error,
      });
    });
};

const postInstrumento = async (req: Request, res: Response) => {
  const { nombre,descripcion,disponibilidad,precio } =
    req.body;

  const Instrumento = new InstrumentoModel({
    _id: new mongoose.Types.ObjectId(),
    nombre,
    descripcion,
    disponibilidad,
    precio,
  });

  await Instrumento
    .save()
    .then((resultado) => {
      return res.status(200).json({
        exito: true,
        datos: resultado,
      });
    })
    .catch((error) => {
      return res.status(500).json({
        exito: false,
        error,
      });
    });
};


export { getInstrumentos, getInstrumento, postInstrumento };
