import tkinter as tk

def salir():
    root.destroy()

def ayuda():
    info = tk.Label(root, text="Python v2.12.5")
    info.pack()

root = tk.Tk()
root.title("Ventana")
root.geometry("300x200")

menuPrincipal = tk.Menu(root)
root.config(menu=menuPrincipal)

menuArchivo = tk.Menu(menuPrincipal, tearoff = 0)
menuPrincipal.add_cascade(label="Archivo", menu=menuArchivo)
menuArchivo.add_command(label="Abrir")
menuArchivo.add_command(label="Salir", command=salir)

menuAyuda = tk.Menu(menuPrincipal, tearoff = 0)
menuPrincipal.add_cascade(label="Ayuda", menu=menuAyuda)
menuAyuda.add_command(label="Acerca de", command=ayuda)
root.mainloop()