import tkinter as tk
from modelo import GameModel
from controlador import GameController

if __name__ == "__main__":
    GameController = GameController
    GameModel = GameModel
    root = tk.Tk()
    root.title("Ventana")
    root.geometry("300x400")

    label = tk.Label(root, text="Visualizador de notas")
    label.pack()
    entrada = tk.Entry(root, width=30)
    entrada.pack()

    root.mainloop()