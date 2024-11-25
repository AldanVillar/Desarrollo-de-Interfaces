import threading
import time
import random
import datetime
from recursos import descargar_imagen

class GameModel:
    def __init__(self, difficulty, player_name, cell_size=100):
        self.difficulty = difficulty
        self.player_name = player_name
        self.cell_size = cell_size
        self.hidden_image = None
        self.board = None
        self.pairs_found = 0
        self.images_loaded = False
        self.images = []
        self._generate_board()
        self._load_images()
        self.start_time = 0
        self.moves = 0

    def _generate_board(self):
        num_pairs = (self.difficulty * self.difficulty) // 2
        cards = list(range(num_pairs)) * 2
        random.shuffle(cards)

    def _load_images(self):
        def load_images_thread():
            self.hidden_image = descargar_imagen("url_base/hidden_image.png")
            for card_id in range(self.difficulty * self.difficulty // 2):
                img_url = f"url_base/{card_id}.png"
                img = descargar_imagen(img_url)
                self.images.append(img)
        threading.Thread(target=load_images_thread, daemon=True).start()

    def images_are_loaded(self):
        return self.images_loaded

    def start_timer(self):
        self.start_time = time.time()

    def get_time(self):
        return round(time.time() - self.start_time, 2)

    def check_match(self, pos1, pos2):
        self.moves += 1
        row1, col1 = pos1
        row2, col2 = pos2
        if self.board[row1][col1] == self.board[row2][col2]:
            self.pairs_found += 1
            return True
        return False

    def is_game_complete(self):
        total_pairs = (self.difficulty * self.difficulty) // 2
        return self.pairs_found == total_pairs

    def save_score(self):
        end_time = self.get_time()
        score = {
            "player_name": self.player_name,
            "difficulty": self.difficulty,
            "moves": self.moves,
            "time": end_time,
            "date": datetime.now()
        }
        scores = self.load_scores()
        if self.difficulty not in scores:
            scores[self.difficulty] = []
        scores[self.difficulty].append(score)
        scores[self.difficulty] = sorted(scores[self.difficulty], key=lambda x: x["moves"])[:3]
        with open("ranking.txt", "w") as file:
            for difficulty in scores:
                for score in scores[difficulty]:
                    file.write(
                        f"{score['player_name']} | {score['difficulty']}x{score['difficulty']} | {score['moves']} moves | {score['time']}s | {score['date']}\n")

    def load_scores(self):
        scores = {4: [], 6: [], 8: []}
        try:
            with open("ranking.txt", "r") as file:
                for line in file:
                    parts = line.strip().split(" | ")
                    player_name = parts[0]
                    difficulty = int(parts[1].split('x')[0])
                    moves = int(parts[2].split(' ')[0])
                    time = float(parts[3].split('s')[0])
                    date = parts[4]

                    score = {
                        "player_name": player_name,
                        "difficulty": difficulty,
                        "moves": moves,
                        "time": time,
                        "date": date
                    }

                    if difficulty in scores:
                        scores[difficulty].append(score)
        except FileNotFoundError:
            pass

        return scores