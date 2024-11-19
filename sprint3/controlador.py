import tkinter as tk
import tkinter
from tkinter import messagebox, simpledialog, Toplevel, Label
from main import Main
from modelo import GameModel
from vista import MainMenu, GameView
import time

class GameController:
    def __init__(self, root):
        self.root = root
        self.model = None
        self.selected = []
        self.timer_started = False
        self.timer = 0
        self.main_menu = MainMenu(self.root, self.show_difficulty_selection, self.show_stats, self.quit_game)

    def show_difficulty_selection(self):
        difficulty = simpledialog.askstring("Seleccionar Dificultad",
                                            "Selecciona la dificultad: fácil, medio o avanzado")
        if difficulty and difficulty.lower() in ['fácil', 'medio', 'avanzado']:
            self.start_game(difficulty.lower())
        else:
            messagebox.showerror("Error", "Por favor, selecciona una dificultad válida.")

    def start_game(self, difficulty):
        player_name = MainMenu.ask_player_name(self)
        if player_name:
            self.show_loading_window("Cargando el juego...")
            self.model = GameModel(difficulty, player_name)
            self.check_images_loaded()

    def show_loading_window(self, message):
        self.loading_window = tk.Toplevel(self.root)
        self.loading_window.title("Cargando...")
        loading_label = tk.Label(self.loading_window, text=message)
        loading_label.pack(padx=20, pady=20)
        self.loading_window.grab_set()

    def check_images_loaded(self):
        if GameModel.images_are_loaded(self):
            self.loading_window.destroy()
            self.game_view = GameView(self.on_card_click, self.update_move_count, self.update_time)
            self.game_view.create_board(self.model)
        else:
            self.root.after(500, self.check_images_loaded)

    def on_card_click(self, pos):
        if not self.timer_started:
            self.timer_started = True
            self.update_time()

        self.selected.append(pos)
        if len(self.selected) == 2:
            self.handle_card_selection()

    def handle_card_selection(self):
        pos1, pos2 = self.selected
        match = GameModel.check_match(pos1, pos2)

        if match:
            self.game_view.update_board(pos1, GameModel.images[pos1])
            self.game_view.update_board(pos2, GameModel.images[pos2])
        else:
            self.game_view.reset_cards(pos1, pos2)
        self.selected.clear()
        self.update_move_count(self.model.get_move_count())
        self.check_game_complete()

    def update_move_count(self, moves):
        GameView.update_move_count(moves)

    def check_game_complete(self):
        if GameModel.is_game_complete():
            messagebox.showinfo("¡Victoria!", "¡Has ganado el juego!")
            self.return_to_main_menu()

    def return_to_main_menu(self):
        self.game_view.destroy()
        self.main_menu = MainMenu(self.root, self.show_difficulty_selection, self.show_stats, self.quit_game)


    def show_stats(self):
        stats = self.model.get_stats()
        MainMenu.show_stats(stats)

    def update_time(self):
        if self.timer_started:
            self.timer += 1
            self.game_view.update_time(self.timer)
            self.root.after(1000, self.update_time)

    def quit_game(self):
        response = messagebox.askyesno("Salir", "¿Estás seguro de que deseas salir?")
        if response:
            self.root.quit()