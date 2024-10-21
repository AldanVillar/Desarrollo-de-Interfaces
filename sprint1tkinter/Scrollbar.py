import tkinter as tk

def parrafo():
    for i in range(1,31):
        cuadroTexto.insert(tk.END, f"Linea {i}\n")

root = tk.Tk()
root.title("Ventana")
root.geometry("300x300")
frame = tk.Frame(root)
frame.pack(fill='both', expand=True)

cuadroTexto = tk.Text(frame, wrap= "none")
cuadroTexto.grid(row=0, column=0, sticky="nsew")
scrollbar = tk.Scrollbar(frame,orient="vertical", command=parrafo)
scrollbar.grid(row=0, column=1, sticky="ns")
cuadroTexto.config(yscrollcommand=scrollbar.set)

frame.grid_rowconfigure(0, weight=1)
frame.grid_columnconfigure(0, weight=1)
parrafo()
root.mainloop()