"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const dotenv_1 = __importDefault(require("dotenv"));
const server_1 = __importDefault(require("./models/server"));
//Config dotenv
dotenv_1.default.config();
//Creamows nuestro servidor
const server = new server_1.default();
//Al encender la app, iniciamos el servidor
server.listen();
//# sourceMappingURL=app.js.map