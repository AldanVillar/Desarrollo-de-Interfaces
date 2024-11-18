import tkinter as tk
import tkinter
from tkinter import messagebox, simpledialog, Toplevel, Label
import main
from modelo import GameModel
from vista import MainMenu, GameView
import time

class GameController:
    def __init__(self, root):
        self.root = MainMenu.root
        self.model = GameModel
        self.selected = []
        self.timer_started = False

    def show_difficulty_selection(self):
        print()

    def start_game(self, difficulty):
        print()

    def show_loading_window(self, message):
        print()

    def check_images_loaded(self):
        print()

    def on_card_click(self, pos):
        print()

    def handle_card_selection(self):
        print()

    def update_move_count(self, moves):
        print()

    def check_game_complete(self):
        print()

    def return_to_main_menu(self):
        print()

    def show_stats(self):
        print()

    def update_time(self):
        print()