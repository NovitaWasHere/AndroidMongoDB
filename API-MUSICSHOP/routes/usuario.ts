import { Router } from "express";
import {
  authUsuario,
  deleteUsuario,
  getUsuario,
  postUsuario,
  putUsuario,
  putShopping

} from "../controllers/usuario";

const router = Router();

//Establecemos los endpoints de cada solicitud
router.get("/esp/:id", getUsuario);
router.post("/agregar", postUsuario);
router.post("/auth", authUsuario);
router.put("/:id", putUsuario);
router.put("/agregarCarrito/:id/ins/:idI",putShopping)
router.delete("/:id", deleteUsuario);

export default router;
