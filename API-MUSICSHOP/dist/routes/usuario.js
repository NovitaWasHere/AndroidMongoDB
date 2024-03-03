"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const usuario_1 = require("../controllers/usuario");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/esp/:id", usuario_1.getUsuario);
router.post("/agregar", usuario_1.postUsuario);
router.post("/auth", usuario_1.authUsuario);
router.put("/:id", usuario_1.putUsuario);
router.put("/agregarCarrito/:id/ins/:idI", usuario_1.putShopping);
router.delete("/:id", usuario_1.deleteUsuario);
exports.default = router;
//# sourceMappingURL=usuario.js.map