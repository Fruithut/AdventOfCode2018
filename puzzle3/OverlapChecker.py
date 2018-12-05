from functools import reduce
import operator

# PARSING
def getInput(file_path: str):
    file_content = []
    for string in open(file_path, 'r', 1):
        values = string.split(" ")
        first_pair = values[2].split(',')
        second_pair = values[3].split('x')
        file_content.append({
            'id': int(values[0][1:]),
            'start_x': int(first_pair[0]), 'start_y': int(first_pair[1][:-1]), 
            'width': int(second_pair[0]), 'height': int(second_pair[1][:-1])
        })
    return file_content

contents = getInput('input.txt')

# PART ONE
def find_multiclaim(claim_input: list):
    fabric = [[0] * 1000 for i in range(1000)]
    for claim in claim_input:
        for x in range(claim['start_x'], claim['start_x'] + claim['width']):
            for y in range(claim['start_y'], claim['start_y'] + claim['height']):
                fabric[x][y] += 1
    return fabric, sum(map(lambda num: 1 if num > 1 else 0, reduce(operator.iconcat, fabric)))

claim_matrix, overlap_count = find_multiclaim(contents)
print('Overlapping: {}'.format(overlap_count))

# PART TWO
def check_overlap(claim: dict):
    '''Checks overlap using matrix with overlap counts for a given point'''
    for x in range(claim['start_x'], claim['start_x'] + claim['width']):
        for y in range(claim['start_y'], claim['start_y'] + claim['height']):
            if claim_matrix[x][y] > 1:
                return True
    return False

def find_non_overlapping(claim_input: list):
    for claim in claim_input:
        if not check_overlap(claim):
            return claim

free_claim = find_non_overlapping(contents)
print('Non-overlapping claim: {}'.format(free_claim))