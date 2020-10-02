# Name: Sherry Xiaoran Guo
# Student #: A01086886 (set 2A)
# ============================
# - please import and run; world refreshes/progresses to next cycle on click.
#
# - the World class is essentially my game board, and contains the array of board cells.
# - given that each plant has exactly 4 plant neighbours and 3 or more empty spaces, it will send seeds to all empty spaces
# - herbivors die after 5 turns without food
# - herbivors will remain at the same location if the square they randomly choose to move to is occupied by another herbivore
#
# FIXED : Plants will seed first before any animals move, so that seeding doesn't happen the same time as moving
