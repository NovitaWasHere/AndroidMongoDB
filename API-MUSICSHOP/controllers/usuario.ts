import { Request, Response } from "express";
import mongoose from "mongoose";
import { UsuarioModel } from "../models/usuario";
import bcrypt from "bcrypt";

const getUsuarios = async (req: Request, res: Response) => {
  await UsuarioModel.find()
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

const getUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;

  await UsuarioModel.findById(id)
    .populate("instrumentos")
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

const authUsuario = async (req: Request, res: Response) => {
  const { correo, contra } = req.body;

  console.log("este es el cuerpo =" + req.body.toString());

  await UsuarioModel.findOne({
    correo: correo,
  })
  .populate("instrumentos")
    .exec()
    .then((resultado) => {
      if (bcrypt.compareSync(contra, resultado!.contra.toString())) {
        console.log("Correcto " + resultado);
        return res.json({
          exito: true,
          datos: resultado,
        });
      } else {
        console.log("Incorrecto " + resultado);
        return res.status(500).json({
          exito: false,
          error: "Contraseña incorrecta",
        });
      }
    })
    .catch((error) => {
      console.log("Error: " + error);
      return res.status(500).json({
        exito: false,
        error,
      });
    });
};

const postUsuario = async (req: Request, res: Response) => {
  const {
    nombre,
    apellidos,
    correo,
    contra,
  } = req.body;

  let hashContra = bcrypt.hashSync(contra, 10);

  console.log(req.body);

  const nuevoUsuario = new UsuarioModel({
    _id: new mongoose.Types.ObjectId(),
    nombre,
    apellidos,
    correo,
    contra: hashContra,
    instrumentos : Array()
  });

  await nuevoUsuario
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

const putUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;
  const { nombre, apellidos, correo, contra} =
    req.body;

  // Verificaciones de atributos nulos
  const object: any = {};
  if (nombre !== "" && nombre !== undefined) {
    object.nombre = nombre;
  }
  if (apellidos !== "" && apellidos !== undefined) {
    object.apellidos = apellidos;
  }
  if (correo !== "" && correo !== undefined) {
    object.correo = correo;
  }

  let hashContra = bcrypt.hashSync(contra, 10);
    object.contra = hashContra

  await UsuarioModel.findOneAndUpdate({ _id: id }, object)
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

const putShopping = async (req: Request, res: Response) => {
  const { id,idI } = req.params;


  await UsuarioModel
    .findByIdAndUpdate(
      id,
      {
        $push: { instrumentos: idI },
      },
      { new: true } 
    )
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


const deleteUsuario = async (req: Request, res: Response) => {
  const { id } = req.params;

  await UsuarioModel.findByIdAndDelete({ _id: id })
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

export {
  getUsuarios,
  getUsuario,
  authUsuario,
  postUsuario,
  putUsuario,
  deleteUsuario,
  putShopping
};
