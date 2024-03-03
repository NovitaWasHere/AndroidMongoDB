import { Router } from "express";
import {
  getInstrumentos,
  getInstrumento,
  postInstrumento,
} from "../controllers/instrumento";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/todos", getInstrumentos);
router.get("/esp/:id", getInstrumento);
router.post("/agregar", postInstrumento);


export default router;
