<p align="center">
 <img height="80" src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/Logo.png">
</p>

<p align="center">
 <img height="100" src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/Quote.png">
</p>

<br>
<h2 align="center">Inspiration</h2>
<br>

Researchers say visual memory is 60,000x times powerful than text/auditory memory and something which is grasped visually remains in our mind for very long time. Our current education system has become outdated and methodological, where teachers prepare their non-interactive lessons and present them in a lecturing and boring fashion. Inspired by the power of visual learning we wanted to create a tool that could converts teacher's lessons into easy to grasp Cliparts as the teacher speaks dynamically, to provide most immersive, engaging and visually enhanced learning experience to students. Hence, we created Visle, a visual learning tool.

<br>
<h2 align="center">What is Visle?</h2>
<br>


Mr Lal is a primary class English teacher. One day he was teaching his students about different kind of birds. He started with the king of birds, Kingfisher🦆 and the conversation went like
```
Mr Lal: "Oncle lived a beautiful kingfisher on a big green tree..."

Hearing this a lot of students raised questions and asked

Student1: "Sir, how beautiful was the bird, the Kingfisher?"
Student2: "Sir, did it have big wings?"
```
Hearing these questions, Mr Lal decided to take help of our visual assistant Visle. As he started teaching again, our tool listened to Mr lal and created cliparts having a beautiful kingfisher, a green tree and a big jungle in the background.

Visle, formed by the words visual-learning, is a tech-based learning tool that converts teacher's speech into Clipart pictures in real-time to provide young minds an enhanced and graphic rich learning experience. Visle does this with help of GANs(Generative adversarial network).

<br>
<h2 align="center">How we build it?</h2>
<br>

Our project has three main elements

**Mobile app** - build with native android sdk and java, it listens to what teacher says and convert their speech to text with Google speech-2-text engine and dispatches it to our api.

**Image generator** - build with pytorch and flask, helps to create magical Clipart of multiple resolutions from the given text and upload these generated images to cloud storage for easy retrieval.

**Clipart board** - designed with html/css/js and websockets, it facilitate the display of generated images in real-time.

**Rest API** - build with nodejs, typescript, mongodb, websockets, it is the heart of the whole system as it connect all other components and synchronize them to work in real-time. It recieves text from the app, send it to image generator for magic to happen, retrieves the generated image from storage and send them to clipart board through a websocket.

<br>
<h2 align="center">Challenges we ran into</h2>
<br>

Our main challenge was to design a generative neural network with following features
 -  Generate images suitable for illustration and teaching 
 -  Generate images for a synonymous words for a rich visual experience 
 -  Generate them rapidly for making it work in real-time 
 -  To make images appropriate to display it to children

The next challenge was to make use of web sockets extensively in variety of tech stacks to make the whole thing work in real-time. Using sockets at such a degree also created a problem of securing them and their coonnection channels.

<br>
<h2 align="center">Accomplishments we are proud of</h2>
<br>

 - Understanding and using GANs to generate Images.
 - Achieving an accuracy of **87.621%** with our generative neural network.
 - Understanding the use channels, websockets for building a real-time applications
 - Using publish-subscrible system design for asynchronous messaging b/w various services
 - serverless archicture for system maintanence and scaling.

<br>
<h2 align="center">What we learned</h2>
<br>

 - GANs(Generative adversarial Network) and training it with images
 - fully automated dockerized deployment with CI/CD pipeline build over github actions
 - Understanding sockets and publish subscribe models to push data
 - Using RabbitMQ as message queue

<p align="center">
 <img src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/remote.png">
</p>
<br>
<br>
<p align="center">
 <img src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/classroom.png">
</p>

                
<br>
<h2 align="center">Neural Network Architecture</h2>

<p align="center">
 <a href="https://openaccess.thecvf.com/content_cvpr_2018/papers/Xu_AttnGAN_Fine-Grained_Text_CVPR_2018_paper.pdf">Reference</a>
</p>
<br>

<p align="center">
 <img src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/GAN.png">
</p>

<br>
<h2 align="center">How it works?</h2>
<br>

<p align="center">
 <img src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/workflow.png">
</p>

<br>
<h2 align="center">Tech Stacks</h2>
<br>

<p align="center">
 <img src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/techstack.png">
</p>

<br>
<h2 align="center">What's next with Visle</h2>
<br>

We want Visle to be easy to use and want it integrate in classrooms, online classrooms and other E-learning tool, so that it could enhance the overall learning experience in classrooms/remote education and thus helps young curious minds develop their powerful visual memory, as our moto is 
                
<p align="center">
 <img height="100" src = "https://raw.githubusercontent.com/IshaanOhri/Visle/master/Assets/img/Quote.png">
</p>

<br>
<br>

# For a live-demo drop a mail at
ishaan99ohri@gmail.com
