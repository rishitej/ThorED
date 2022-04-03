import openai
from gpt import GPT
from gpt import Example
from flask import Flask,request, jsonify, render_template

openai.api_key= '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'

gpt = GPT(engine="davinci", temperature=0.5, max_tokens=350)

gpt.add_example(Example('''The very title of the story suggests that it is about Christmas. The opening of the story initiates the theme. The writer at the outset tells that Scrooge and Marley were partners by profession for a very long period and Marley was now dead. After his death all the legal rights were in the sole possession of Scrooge. The writer refers to the ghost of Hamlet’s father to prepare the readers that the story will be about spirits at the centre of the story.

We are told that Scrooge is a hard-hearted, old and covetous sinner. The coldness of heart and cruelty of head were prominent on the face and figure of Scrooge. He is an isolated figure and does not enjoy any kind of social relations. Neither human beings nor animals had a fellow feeling with him. The story is set on a Christmas Eve. Old Scrooge is shown busy in his counting-house. It was late afternoon and the clerk in the counting-house was copying letters in candle light. Everything was dull and damp when Scrooge suddenly heard a cheerful voice of his nephew wishing him ‘a merry Christmas’. Scrooge retorts coldly and cruelly and wishes that everybody wishing merry Christmas should be killed. Both the uncle and the nephew have a heated argument over the issue and when the clerk tries to interfere he is bluntly silenced by Scrooge. At the departure of Scrooge’s nephew two people enter the office and ask for some donation to help the poor and destitutes. The two gentlemen too are rebuked and forced out without a single penny from Scrooge’s pockets.

Next came a singer of Christmas Carol, but he too was forced to run away in fear lest he should be hit by Scrooge. By this time the hour of departure of the clerk arrived and after along debate he is provided with the next day’s holiday though quite reluctantly on the part of Scrooge. The office was closed and Scrooge went to his residence after having meals. As he reached the door of his bedroom he had a strange feeling of seeing Marley’s face on the door. He was surprised and frightened but soon discarded the feeling of fear considering it to be nothing but a hallucination of his mind.''','''A Christmas Carol is the most read story of Charles Dickens. The story shows the reform of the miserly Scrooge, which has become one of the greatest symbols of humanity.'''))

gpt.add_example(Example('''Goa is very much influenced by the Portuguese. Their traditional work can be still seen there. The Portuguese are famous for preparing the loaves of bread. We can come across the bakers of bread. The writer tells about his childhood days in Goa when the baker used to visit their friend. He used to visit the house twice a day. In the morning, his jingling sound of the bamboo woke them from sleep. They all ran to meet him. The loaves were purchased by the man-servant of the house. The villagers were much fond of the sweet bread known as ‘bol’. The marriage gifts were meaningless without it. So the bakers’ furnace in the village was the most essential thing. The lady of the house prepared sandwiches on the occasion of her daughter’s engagement. In those days the bread sellers wore a particular dress known as ‘Kabai’. It was a single piece long frock up to the knees. Even today, they can be seen wearing a half pant that reaches just below the knees. People usually comment that he is dressed like a ‘pader’. Baking was a profitable profession in the old days. The baker and his family never starved and they looked happy and prosperous.''','''‘A Baker from Goa’ is a pen portrait of a traditional Goan village baker who still has an important place in his society. The narrator is travelling through the memory lane thinking about the loaves of bread a baker delivered every morning.'''))

gpt.add_example(Example('''It was early morning when the author left Ravu. He told Lhamo that he was going towards Mount Kailash to complete the Kora. At this Lhamo gave him a gift of a long sleeved sheep skin coat.

Tsetan was the driver of their vehicle. He knew the route to Mount Kailash. He told the author that if there was no snow there would be no problem, Tsetan took a short cut from Ravu. This took them across the vast open grassy plains to the stony plains. The air was clean. Sometimes they saw a few gazelles and wild asses.

The way through the hills started. On the way they met solitary drokpa tending their flocks. Seeing the car they would pause and stare at it. They also came across nomads’ dark tents. Giant Tibetan mastiffs stood guard before these tents. These dogs would chase the author’s car for a hundred metres or so.

They could see snow capped mountains far away as they entered a valley where the river was wide. The turns now became sharper and the ride more uncomfortable. After a while the driver Tsetan had to stop. The author’s companion Daniel also came out of the car. There was snow capped route ahead for about fifteen metres, after which the dusty trail could be seen again. The three men took handfuls of dirt and flung it on the icy surface. Then Tsetan got in and drove the car carefully across the dusty snow. It happened at the height of 5210 metres.

There was another blockage after about ten minutes but somehow. Tsetan negotiated it. The author felt a terrible headache.''','''The writer, Nick Middleton, describes his pilgrimage to Mount Kailash. He wants to be more adventurous than to be religious. As he starts from Ravu towards Kailash he describes all the things he sees—landscape, people and animals. He goes to Mount Kailash to do the Kora with other pilgrims.'''))


app = Flask(__name__)

@app.route('/', methods=['POST','GET'])
def find_answer():
    print('here')
    prompt = request.args.get('prompt')
#     prompt = request.get_json()['prompt']
    response = gpt.get_top_reply(prompt)
    ans=response.split('output:')[1].strip()
    resp = jsonify({'code':ans})
    print(resp.json)
    resp.status_code = 200
    return resp

if __name__=='__main__':
    app.run(host='0.0.0.0',port=7000,debug=True)
