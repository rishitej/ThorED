import openai
from gpt import GPT
from gpt import Example
from flask import Flask,request, jsonify, render_template

openai.api_key= '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'

gpt = GPT(engine="davinci", temperature=0.75, max_tokens=300)

gpt.add_example(Example('''I got up in the middle of the night, I was sweating profusely''','''I got up in the middle of the night, I was sweating profusely. I switched on the light only to find that there was some kind of electricity failure. I came out of the room to get some fresh air. It was stormy outside and that might have been the reason for the electricity failure. I drank cold water, took a chair, and sat in the verandah. Suddenly, I heard a loud shriek. It was coming from our neighbour’s house. I was so confused, now what to do. Then I gathered courage and went inside to fetch a torch. I took the torch and started moving towards my neighbour’s house.

I peeped inside from the window and saw two people who were covering the owners at gunpoint. I stepped back and started thinking of my next step. At that moment I heard a motorbike approaching near. It was cobra police on their patrolling. I signalled to them. They stopped and asked me the reason for stopping them. I told them the whole story. They immediately went inside to help the old couple. The miscreants were shocked at the sudden arrival of the police. They tried to escape but the policemen took prompt action and caught them. We heaved a sigh of relief. Suddenly, the whole area was illuminated as the power supply had resumed. The old couple saw me and thanked me for my sharp wittedness.'''))

gpt.add_example(Example('''Knowledge is power. Based on the following visual along with your ideas, develop a story based on the theme of helping children from economically weaker sections of society (EWS) and below the poverty line''','''For the past few days, I had been observing a thin, frail girl come and sit on the bench and look continuously for hours together at the passers-by without playing or talking to anyone. From her appearance, I concluded that she was from some economically weaker section of society. That day I could not control myself and went to talk to the sweet girl. After talking to her I found that her father had died in some clash during street violence and that her mother had gone out to work. Her name was Khushboo. When I talked to her she replied with jee’ every time which showed that she was a cultured girl. I pointed towards a house and told her that I stayed there and asked her if she wanted to come along with me. The girl thought for a while and then held my finger.

I was elated. I offered her something to eat which she ate after washing her hands. All my family members were impressed by that sweet girl. I showed her some storybooks with illustrations that she looked at very carefully. I asked her if she went to school. She told that her mother could not send her to school as they were very poor. Tears filled in her large innocent eyes, I decided to teach her.

As soon as I reached home from my college, she would run to me. This became a routine. I was glad to find that she was a lover of books. For hours together, she would turn pages of storybooks and enquire in detail about what was being spoken by the characters. Her desire to learn led me to ponder if I could ever teach her. And one day I held her tender fingers to teach her to write. Since that day there has been no looking back. We both share a very wonderful relation with each other. My mother says that she looks like my sister. And I have pledged to educate Khushboo.'''))

gpt.add_example(Example('''It was a hot day. A blind man was crossing the road, suddenly a car came''','''It was a hot summer day. People were busy running around searching for cooler places to escape the scorching heat. Those who could not and were stuck in their offices, were returning home, tired and withered like a dried plant. People returning home were trying to drive fast in order to reach home and be in the cool comfort of their homes. Mr. Jain was returning home after his monthly visit to the bank, to collect his pension.

His wife was at home. His son had worked hard at college and was now studying to be a doctor. He wanted to help restore his father’s sight. The blind man’s eyes welled up with tears thinking about his son. Lost in his thoughts, carelessly he started crossing the busy road. Suddenly a loud screeching of brakes was heard. Everyone rushed to see what the whole commotion was all about. Mr. Jain was lying helplessly on the road. Someone from the crowd came forward and helped him sit up.

The motorist had applied brakes at the right time and that had saved the old man. She rushed out of her car and seated the old man into her car. She apologized profusely and took him to the hospital. The doctors said that he was perfectly fine. The motorist then decided to drop the old man home. There she saw his wife and the photo of his son. It was her best friend Ankit Jain. She was grateful to God that Mr. Jain was absolutely safe.'''))

gpt.add_example(Example('''Aman – sixteen years old – returns after school – next door building – thick smoke from window – old couple lives there – A man must take immediate action.''','''Aman said bye to his friends Nikhil and Nysa and got down from the bus. It had been a hectic day. Exams were round the corner and his teachers had started taking regular tests. On Monday was the dreaded subject, Mathematics. Aman knew he had to work hard to get good marks. His parents were working hard so that he could secure admission in a good school. He reached home and opened the door with his key. He was just about to drink water when he noticed thick smoke bellowing from the window of the adjacent building.

Mr and Mrs Sikand lived there. They were old. Aman often visited them. They also loved him and had gifted him a bat on his sixteenth birthday. Aman ran to the building and alerted the watchman. Both of them rushed upstairs and knocked on the door. After repeated knocks when there was no response they broke it open along with the other neighbours, who had gathered there by then. They found Mr and Mrs Sikand on the floor. Aman found the source of the smoke–a short circuit.

He informed his parents and took the old couple along with another neighbor to the hospital. The doctor examined them and said that they were fine and would be discharged soon. Aman went to meet them. He told them what had happened and asked them to be careful in the future. The Sikands thanked Aman and promised to be careful in the future.'''))

gpt.add_example(Example('''Meena is a 12-year-old girl. She is staying with her mother. No one was there for their help. One day a stranger came to Meena’s house. Her mother was not in the house at that time. The man caught hold of Meena. She began to cry loudly. But no one was there to hear her.''','''Meena was a twelve-year-old girl who used to stay with her mother. Her father had gone to another city to earn money. One day, a stranger came to Meena’s house. Her mother was not in the house at that moment. The man caught hold of Meena. She began to cry loudly but no one was there to hear her. Suddenly, Meena was reminded of her mother’s teaching of not losing the wits and alertness of mind when in hour of need. She built up her confidence and looked around. She could not see anything with which to hit the stranger. Suddenly, she bit the stranger on his arm. The stranger cried with pain and let go of her. This moment was important for her. She took her mother’s saree and tied it around the stranger’s neck. Now, it was the time for the stranger to cry for help. She tied him and then used her mobile phone to call the police as well as her neighbours. The police reached within no time and arrested the man. The neighbours had also arrived. They all patted Meena on her back for fighting bravely with the man.'''))


app = Flask(__name__)

@app.route('/', methods=['POST','GET'])
def find_answer():
    prompt = request.args.get('prompt')
#     prompt = request.get_json()['prompt']
    response = gpt.get_top_reply(prompt)
    ans=response.split('output:')[1].strip()
    resp = jsonify({'code':ans})
    print(resp.json)
    resp.status_code = 200
    return resp

if __name__=='__main__':
    app.run(host='0.0.0.0',port=9000,debug=True)
