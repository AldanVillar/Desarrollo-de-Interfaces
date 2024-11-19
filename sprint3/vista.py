import tkinter as tk
from modelo import GameModel
from tkinter import simpledialog


class GameView:
    def __init__(self, on_card_click_callback, update_move_count_callback, update_time_callback):
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback
        self.labels = []
        self.move_count_label = None
        self.time_label = None

    def create_board(self, model):
        self.ventana = tk.Toplevel()
        self.ventana.title("Juego de Memoria")
        fil, column = GameModel.difficulty
        self.labels = []
        for i in range(fil):
            row_labels = []
            for j in range(column):
                label = tk.Label(self.ventana, width=10, height=5, bg="gray", relief="raised")
                label.grid(row=i, column=j, padx=5, pady=5)
                label.bind("<Button-1>", lambda event, pos=(i, j): self.on_card_click(pos))
                row_labels.append(label)
            self.labels.append(row_labels)

        self.move_count_label = tk.Label(self.ventana, text="Movimientos: 0")
        self.move_count_label.grid(row=fil, column=0, columnspan=fil, pady=5)

        self.time_label = tk.Label(self.ventana, text="Tiempo: 0")
        self.time_label.grid(row=fil + 1, column=0, columnspan=fil, pady=5)

    def update_board(self, pos, image_id):
        self.on_card_click_callback(pos)
        fil, column = pos
        self.labels[fil][column].config(text=image_id, bg="white")

    def reset_cards(self, pos1, pos2):
        fil1, column1 = pos1
        fil2, column2 = pos2
        self.labels[fil1][column1].config(text="", bg="gray")
        self.labels[fil2][column2].config(text="", bg="gray")

    def update_move_count(self, moves):
        if self.move_count_label:
            self.move_count_label.config(text=f"Movimientos: {moves}")
        if self.update_move_count_callback:
            self.update_move_count_callback(moves)

    def update_time(self, time):
        if self.time_label:
            self.time_label.config(text=f"Tiempo: {time}")
        if self.update_time_callback:
            self.update_time_callback(time)

    def destroy(self):
        if self.ventana:
            self.ventana.destroy()
        self.labels = []

class MainMenu:
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        self.root = root
        self.start_game_callback = start_game_callback
        self.show_stats_callback = show_stats_callback
        self.quit_callback = quit_callback
        self.root.title("Menú Principal")
        self.title_label = tk.Label(self.root, text="Juego de Memoria")
        self.title_label.pack(pady=20)
        self.start_button = tk.Button(self.root, text="Jugar", width=20, height=2,command=self.start_game)
        self.start_button.pack(pady=10)
        self.stats_button = tk.Button(self.root, text="Estadísticas", width=20, height=2,command=self.show_stats)
        self.stats_button.pack(pady=10)
        self.quit_button = tk.Button(self.root, text="Salir", width=20, height=2,command=self.quit_game)
        self.quit_button.pack(pady=10)

    def ask_player_name(self):
        player_name = simpledialog.askstring("Nombre del Jugador", "Introduce tu nombre:")
        return player_name

    def show_stats(self, stats):
        stats = self.show_stats_callback()
        if stats:
            stats_window = tk.Toplevel(self.root)
            stats_window.title("Estadísticas")
            difficulty_levels = sorted(stats.keys())
            for level in difficulty_levels:
                level_label = tk.Label(stats_window, text=f"Nivel {level}")
                level_label.pack(pady=5)
                score_list = stats[level]
                for entry in score_list:
                    name, moves = entry
                    score_label = tk.Label(stats_window, text=f"{name} - Movimientos: {moves}")
                    score_label.pack()
                separator = tk.Label(stats_window, text="-" * 30)
                separator.pack(pady=10)