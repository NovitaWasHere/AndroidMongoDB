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
const express_1 = __importDefault(require("express"));
const mongoose_1 = __importDefault(require("mongoose"));
const cors_1 = __importDefault(require("cors"));
const usuario_1 = __importDefault(require("../routes/usuario"));
const instrumento_1 = __importDefault(require("../routes/instrumento"));
class Servidor {
    constructor() {
        this.rutasApi = {
            instrumentos: "/api/instrumentos/",
            usuarios: "/api/usuarios/",
        };
        this.app = (0, express_1.default)();
        this.port = "3000";
        this.establecerConexionBD();
        this.middlewares();
        this.routes();
    }
    establecerConexionBD() {
        return __awaiter(this, void 0, void 0, function* () {
            mongoose_1.default
                .connect(process.env.MONGO_URL)
                .then(() => {
                console.log("Éxito al conectar a Mongo");
            })
                .catch((error) => {
                console.log("Error al conectar a Mongo" + error);
            });
            mongoose_1.default.set("debug", true);
        });
    }
    middlewares() {
        //CORS
        this.app.use((0, cors_1.default)());
        //Parseo del body
        this.app.use(express_1.default.json());
        //Codificar url
        this.app.use(express_1.default.urlencoded({ extended: true }));
    }
    routes() {
        this.app.use(this.rutasApi.usuarios, usuario_1.default);
        this.app.use(this.rutasApi.instrumentos, instrumento_1.default);
    }
    listen() {
        this.app.listen(this.port, () => {
            console.log("El servidor activo en puerto " + this.port);
        });
    }
}
//Exportamos la clase
exports.default = Servidor;
//# sourceMappingURL=server.js.map