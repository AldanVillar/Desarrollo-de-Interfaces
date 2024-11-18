import tkinter as tk
from modelo import GameModel


class GameView:
    def __init__(self, on_card_click_callback, update_move_count_callback, update_time_callback):
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback
        self.labels = 0

    def create_board(self, model):
        print()

    def update_board(self, pos, image_id):
        print()

    def reset_cards(self, pos1, pos2):
        print()

    def update_move_count(self, moves):
        print()

    def update_time(self, time):
        print()

    def destroy(self):
        print()

class MainMenu:
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        self.root = root
        self.start_game_callback = start_game_callback
        self.show_stats_callback = show_stats_callback
        self.quit_callback = quit_callback

    def ask_player_name(self):
        print()

    def show_stats(self, stats):
        print()