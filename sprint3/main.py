import tkinter as tk
from modelo import GameModel
from controlador import GameController

class Main:
    root = tk.Tk()
    controller = GameController(root)
    gameModel = GameModel(1,"Player")
    root.mainloop()

if __name__ == "__main__":
    Main()