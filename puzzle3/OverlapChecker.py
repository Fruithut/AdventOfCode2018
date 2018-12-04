from collections import defaultdict
import numpy

def getInput(file_path: str):
    file_content = []
    for string in open(file_path, 'r', 1):
        values = string.split(" ")
        first_pair = values[2].split(',')
        second_pair = values[3].split('x')
        file_content.append({ 
            'x_offset': int(first_pair[0]), 
            'y_offset': int(first_pair[1][:-1]), 
            'width': int(second_pair[0]), 
            'height': int(second_pair[1][:-1])
        })
    return file_content

def generateHitMatrix(claim_specifications: [{}]):
    claim_matrix = numpy.zeros((1000,1000))
    for line in claim_specifications:
        for x in range(line['x_offset'], line['x_offset'] + line['width']):
            for y in range(line['y_offset'], line['y_offset'] + line['height']):
                claim_matrix[x][y] += 1
    return claim_matrix

def countMultipleClaims(claim_matrix: numpy.ndarray):
    claim_hits = 0
    for i in claim_matrix:
        for j in i:
            if j > 1:
                claim_hits += 1
    return claim_hits

# PART ONE
contents = getInput('input.txt')
hit_matrix = generateHitMatrix(contents)
hits = countMultipleClaims(hit_matrix)
print(hits)

# PART TWO

    