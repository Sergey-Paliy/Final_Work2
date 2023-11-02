import json
import os
from datetime import datetime

# Путь к файлу, где будут храниться заметки в формате JSON
data_file = "notes.json"

def load_notes():
    if os.path.exists(data_file):
        with open(data_file, "r") as file:
            return json.load(file)
    return []

def save_notes(notes):
    with open(data_file, "w") as file:
        json.dump(notes, file, indent=4)

def add_note(title, body):
    notes = load_notes()
    note = {
        "id": len(notes) + 1,
        "title": title,
        "body": body,
        "timestamp": datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    }
    notes.append(note)
    save_notes(notes)
    print("Заметка добавлена!")

def view_notes():
    notes = load_notes()
    for note in notes:
        print(f"ID: {note['id']}")
        print(f"Заголовок: {note['title']}")
        print(f"Тело заметки: {note['body']}")
        print(f"Дата/время: {note['timestamp']}")
        print("")

def delete_note(note_id):
    notes = load_notes()
    notes = [note for note in notes if note['id'] != note_id]
    save_notes(notes)
    print(f"Заметка с ID {note_id} удалена!")

def filter_notes_by_date(start_date, end_date):
    notes = load_notes()
    filtered_notes = [note for note in notes if start_date <= note['timestamp'] <= end_date]
    for note in filtered_notes:
        print(f"ID: {note['id']}")
        print(f"Заголовок: {note['title']}")
        print(f"Тело заметки: {note['body']}")
        print(f"Дата/время: {note['timestamp']}")
        print("")

def main():
    while True:
        print("\nВыберите действие:")
        print("1. Добавить заметку")
        print("2. Просмотреть заметки")
        print("3. Удалить заметку")
        print("4. Фильтр по дате")
        print("5. Выйти")
        choice = input("Введите номер действия: ")

        if choice == "1":
            title = input("Введите заголовок: ")
            body = input("Введите тело заметки: ")
            add_note(title, body)
        elif choice == "2":
            view_notes()
        elif choice == "3":
            note_id = int(input("Введите ID заметки для удаления: "))
            delete_note(note_id)
        elif choice == "4":
            start_date = input("Введите начальную дату (ГГГГ-ММ-ДД): ")
            end_date = input("Введите конечную дату (ГГГГ-ММ-ДД): ")
            filter_notes_by_date(start_date, end_date)
        elif choice == "5":
            break
        else:
            print("Неверный выбор. Попробуйте снова.")

if __name__ == "__main__":
    main()

