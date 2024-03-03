"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const express_1 = require("express");
const instrumento_1 = require("../controllers/instrumento");
const router = (0, express_1.Router)();
//Establecemos los endpoints de cada solicitud
router.get("/todos", instrumento_1.getInstrumentos);
router.get("/esp/:id", instrumento_1.getInstrumento);
router.post("/agregar", instrumento_1.postInstrumento);
exports.default = router;
//# sourceMappingURL=instrumento.js.map