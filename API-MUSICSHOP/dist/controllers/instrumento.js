"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.postInstrumento = exports.getInstrumento = exports.getInstrumentos = void 0;
const mongoose_1 = __importDefault(require("mongoose"));
const instrumento_1 = require("../models/instrumento");
const getInstrumentos = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    yield instrumento_1.InstrumentoModel.find()
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
});
exports.getInstrumentos = getInstrumentos;
const getInstrumento = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { id } = req.params;
    yield instrumento_1.InstrumentoModel.findById(id)
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
});
exports.getInstrumento = getInstrumento;
const postInstrumento = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    const { nombre, descripcion, disponibilidad, precio } = req.body;
    const Instrumento = new instrumento_1.InstrumentoModel({
        _id: new mongoose_1.default.Types.ObjectId(),
        nombre,
        descripcion,
        disponibilidad,
        precio,
    });
    yield Instrumento
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
});
exports.postInstrumento = postInstrumento;
//# sourceMappingURL=instrumento.js.map