package services;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import model.Article;

/**
 * Created by Alexandru on 4/23/2017.
 */

public class ArticleService {


    private final String LOG_TAG = this.getClass().getName();
    private String JSON2 = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":122705,\"startIndex\":1,\"pageSize\":2,\"currentPage\":1,\"pages\":61353,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"travel/2017/mar/12/great-little-ski-resorts-val-darly-france-mont-blanc-savoy\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2017-03-12T10:00:00Z\",\"webTitle\":\"Great little ski resorts: Val d’Arly, France\",\"webUrl\":\"https://www.theguardian.com/travel/2017/mar/12/great-little-ski-resorts-val-darly-france-mont-blanc-savoy\",\"apiUrl\":\"https://content.guardianapis.com/travel/2017/mar/12/great-little-ski-resorts-val-darly-france-mont-blanc-savoy\",\"fields\":{\"headline\":\"Great little ski resorts: Val d’Arly, France\",\"standfirst\":\"<p>This ribbon of traditional Alpine villages between Mont Blanc and the Aravis is cheaper, more relaxed and feels more French than the big, glam resorts neaby. Perfect for families</p>\",\"trailText\":\"This ribbon of traditional Alpine villages between Mont Blanc and the Aravis is cheaper, more relaxed and feels more French than the big, glam resorts neaby. Perfect for families\",\"byline\":\"Mary Novakovich\",\"main\":\"<figure class=\\\"element element-image\\\" data-media-id=\\\"a68bac54d9bfb192dff78f32e715f521716225fa\\\"> <img src=\\\"https://media.guim.co.uk/a68bac54d9bfb192dff78f32e715f521716225fa/667_305_4261_2557/1000.jpg\\\" alt=\\\"Skiers near Notre-Dame-de-Bellecombe.\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> <figcaption> <span class=\\\"element-image__caption\\\">Darling d’Arly … skiers enjoy plenty of snow near Notre-Dame-de-Bellecombe</span> </figcaption> </figure>\",\"body\":\"<h2><strong>Er, where?</strong><br /></h2> <p>Just 5km south of impossibly chic Megève, you reach the first of four villages that make up Val d’Arly – Praz-sur-Arly – swiftly followed by Flumet, Notre-Dame-de-Bellecombe and Crest-Voland. With the glamour of Megève and Chamonix so close by, it’s easy to miss this collection of traditional villages tucked in between Mont Blanc and the Aravis range. But between them they cover 120km of pistes, and they’re connected to the larger Espace Diamant which includes Les Saisies.</p> <h2><strong>Why try it?</strong><br /></h2> <p>You get gorgeous views of Mont Blanc for considerably less than you would pay in Val d’Arly’s starrier neighbours. The vibe in the villages is relaxed, friendly, solidly French and geared towards families – they’re still very much farming communities that run all year round. With lots of recent snow in the Alps it’s a good bet for a last-minute Easter ski break this year. And it’s hard to resist a place that runs a festival that allows you to ski with eagles: the next <a href=\\\"http://www.valdarly-montblanc.com/fr/hiver/agenda/nouveautes/festival-aigles-a-ski\\\">Festival Aigles à Ski</a> is between 5-8 March 2018.</p> <h2><strong>Mountain lowdown</strong><br /></h2>  <figure class=\\\"element element-image element--showcase\\\" data-media-id=\\\"0f9686136565eb4ee3bde4dcaa03993ff6bad80a\\\"> <img src=\\\"https://media.guim.co.uk/0f9686136565eb4ee3bde4dcaa03993ff6bad80a/0_108_4352_2611/1000.jpg\\\" alt=\\\"Val d’Arly, France\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> </figure>  <p>Notre-Dame and Crest-Voland – linked by a free ski bus – have the lion’s share of the slopes, most of which are sheltered, wide, tree-lined pistes. Crest-Voland’s long green runs make it a better bet for newbies, while more confident skiers can tackle the more challenging blues and tough reds in Notre-Dame. Expert skiers have only a handful of black runs, but there’s good off-piste skiing as well as ski touring. Both Notre-Dame and Crest-Voland have direct links to much larger Les Saisies, which you can add to your lift pass, and two snowparks and six boardercross courses are scattered among the four villages. There are no cable cars, but chairlifts at the base of each village take you up the slopes.</p> <h2><strong>And apart from skiing?</strong><br /></h2> <p>When the snow is good, there are lots of fun non-ski activities: hop on a snow-scoot (a cross between scooter and snowboard), fat bike, snake glisse (trains of linked toboggans) and modern variations on sledges such as the single-runner yooner. You might spot some chamois on a snowshoe walk around Col des Aravis, which take place on Wednesday mornings. If the snow is too thin for dog-sledding in Notre-Dame, the exhilarating rides take place in the forests of Les Saisies. People of all ages flock to the outdoor ice-skating rink in Notre-Dame, which is open from 4-8pm. There are also guided visits to local cheese farms, which add to the region’s supply of <em>tomme de Savoie</em> and Beaufort.</p> <h2><strong>Après any good?</strong><br /></h2>  <figure class=\\\"element element-image\\\" data-media-id=\\\"f68ca1544d5f8d15255590abf997508c1a3eef27\\\"> <img src=\\\"https://media.guim.co.uk/f68ca1544d5f8d15255590abf997508c1a3eef27/0_237_2048_1228/1000.jpg\\\" alt=\\\"L’Ambiance, Notre-Dame, France\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> <figcaption> <span class=\\\"element-image__caption\\\">L’Ambiance</span> </figcaption> </figure>  <p>This certainly isn’t the place for nightlife, although <a href=\\\"https://www.facebook.com/LAmbiance-By-night-981546975229710/\\\">L’Ambiance</a> in Notre-Dame can be lively in the evenings when there’s live music. There’s also a piano bar at <a href=\\\"http://www.lofficiel.net/bar-pub-terre-neuve_8_12302.aspx\\\">Terre Neuve</a> in Crest-Voland and a good vibe at <a href=\\\"http://www.pub-pizzeria-shamrock.fr/fr\\\">Le Shamrock</a> in Flumet.</p> <h2><strong>Where do I eat?</strong><br /></h2> <p>Try the delicious Beaufort fondue at <a href=\\\"https://www.petitfute.com/v36448-notre-dame-de-bellecombe-73590/c1165-restaurants/c4-cuisine-francaise/219125-l-equipe.html\\\">L’Equipe</a> (+33 4 7931 6126) in the centre of Notre-Dame, or the baked cheese at <a href=\\\"https://www.facebook.com/Le-Bistrot-de-Julien-342843425846156/\\\">Le Bistrot de Julien</a> at the bottom of Mont-Rond. For a sunny mountain lunch in Crest-Voland, take the Logère chairlift to <a href=\\\"http://labellemetairie.com/\\\">La Belle Métairie</a>, which has refined seafood as well as cheesy Savoy favourites.</p> <h2><strong>Where do I stay?</strong><br /></h2> <p>Le Village is a traditional, simple self-catering residence in the centre of Notre-Dame, with kitchens and an indoor pool. A week’s rental with <a href=\\\"http://www.peakretreats.co.uk/winter/ski-resorts/mont-blanc-chamonix-valley/notre-dame-de-bellecombe/le-village\\\">Peak Retreats</a> costs from £481 for an apartment sleeping up to four, including Eurotunnel. The village’s only hotel, <a href=\\\"http://www.hotel-lamolliniere.com/fr/index.aspx\\\">La Mollinière</a>, which includes a good restaurant, is at the foot of the Reguet lifts and has doubles from €77, room only. <a href=\\\"http://www.savoie-mont-blanc.com/offre/fiche/hotel-pension-plein-soleil/210482\\\">Hotel Pension Plein Soleil</a> is a handy base for Crest-Voland’s Logère chairlift, with doubles from €72 half board.<br /></p> <h2><strong>The nitty gritty</strong><br /></h2> <p>120km of pistes, 51 lifts, highest point 2,069m</p> <p><strong>Good for <br /></strong>Families, friendly ambience, French atmosphere</p> <p><strong>Limitations<br /></strong>Low altitude means it’s sometimes reliant on snow cannons</p> <p><strong>Cost <br /></strong>Lift pass (6 days): adult €158, child €122. Beer: €4</p> <p>Green runs 21 | Blue runs 38 | Red runs 34 | Black runs 4</p>\",\"newspaperPageNumber\":\"10\",\"wordcount\":\"691\",\"commentCloseDate\":\"2017-03-15T10:00:00Z\",\"commentable\":\"true\",\"firstPublicationDate\":\"2017-03-12T10:00:00Z\",\"isInappropriateForSponsorship\":\"false\",\"isPremoderated\":\"false\",\"lastModified\":\"2017-03-14T10:43:13Z\",\"newspaperEditionDate\":\"2017-03-11T00:00:00Z\",\"productionOffice\":\"UK\",\"publication\":\"The Guardian\",\"shortUrl\":\"https://gu.com/p/64xp8\",\"shouldHideAdverts\":\"false\",\"showInRelatedContent\":\"true\",\"thumbnail\":\"https://media.guim.co.uk/a68bac54d9bfb192dff78f32e715f521716225fa/667_305_4261_2557/500.jpg\",\"legallySensitive\":\"false\",\"lang\":\"en\",\"bodyText\":\"Er, where? Just 5km south of impossibly chic Megève, you reach the first of four villages that make up Val d’Arly – Praz-sur-Arly – swiftly followed by Flumet, Notre-Dame-de-Bellecombe and Crest-Voland. With the glamour of Megève and Chamonix so close by, it’s easy to miss this collection of traditional villages tucked in between Mont Blanc and the Aravis range. But between them they cover 120km of pistes, and they’re connected to the larger Espace Diamant which includes Les Saisies. Why try it? You get gorgeous views of Mont Blanc for considerably less than you would pay in Val d’Arly’s starrier neighbours. The vibe in the villages is relaxed, friendly, solidly French and geared towards families – they’re still very much farming communities that run all year round. With lots of recent snow in the Alps it’s a good bet for a last-minute Easter ski break this year. And it’s hard to resist a place that runs a festival that allows you to ski with eagles: the next Festival Aigles à Ski is between 5-8 March 2018. Mountain lowdown Notre-Dame and Crest-Voland – linked by a free ski bus – have the lion’s share of the slopes, most of which are sheltered, wide, tree-lined pistes. Crest-Voland’s long green runs make it a better bet for newbies, while more confident skiers can tackle the more challenging blues and tough reds in Notre-Dame. Expert skiers have only a handful of black runs, but there’s good off-piste skiing as well as ski touring. Both Notre-Dame and Crest-Voland have direct links to much larger Les Saisies, which you can add to your lift pass, and two snowparks and six boardercross courses are scattered among the four villages. There are no cable cars, but chairlifts at the base of each village take you up the slopes. And apart from skiing? When the snow is good, there are lots of fun non-ski activities: hop on a snow-scoot (a cross between scooter and snowboard), fat bike, snake glisse (trains of linked toboggans) and modern variations on sledges such as the single-runner yooner. You might spot some chamois on a snowshoe walk around Col des Aravis, which take place on Wednesday mornings. If the snow is too thin for dog-sledding in Notre-Dame, the exhilarating rides take place in the forests of Les Saisies. People of all ages flock to the outdoor ice-skating rink in Notre-Dame, which is open from 4-8pm. There are also guided visits to local cheese farms, which add to the region’s supply of tomme de Savoie and Beaufort. Après any good? This certainly isn’t the place for nightlife, although L’Ambiance in Notre-Dame can be lively in the evenings when there’s live music. There’s also a piano bar at Terre Neuve in Crest-Voland and a good vibe at Le Shamrock in Flumet. Where do I eat? Try the delicious Beaufort fondue at L’Equipe (+33 4 7931 6126) in the centre of Notre-Dame, or the baked cheese at Le Bistrot de Julien at the bottom of Mont-Rond. For a sunny mountain lunch in Crest-Voland, take the Logère chairlift to La Belle Métairie, which has refined seafood as well as cheesy Savoy favourites. Where do I stay? Le Village is a traditional, simple self-catering residence in the centre of Notre-Dame, with kitchens and an indoor pool. A week’s rental with Peak Retreats costs from £481 for an apartment sleeping up to four, including Eurotunnel. The village’s only hotel, La Mollinière, which includes a good restaurant, is at the foot of the Reguet lifts and has doubles from €77, room only. Hotel Pension Plein Soleil is a handy base for Crest-Voland’s Logère chairlift, with doubles from €72 half board. The nitty gritty 120km of pistes, 51 lifts, highest point 2,069m Good for Families, friendly ambience, French atmosphere Limitations Low altitude means it’s sometimes reliant on snow cannons Cost Lift pass (6 days): adult €158, child €122. Beer: €4 Green runs 21 | Blue runs 38 | Red runs 34 | Black runs 4\",\"charCount\":\"3867\"},\"isHosted\":false},{\"id\":\"travel/2017/feb/26/great-little-ski-resorts-vaujany-france-alps\",\"type\":\"article\",\"sectionId\":\"travel\",\"sectionName\":\"Travel\",\"webPublicationDate\":\"2017-02-26T10:00:27Z\",\"webTitle\":\"Great little ski resorts: Vaujany, France\",\"webUrl\":\"https://www.theguardian.com/travel/2017/feb/26/great-little-ski-resorts-vaujany-france-alps\",\"apiUrl\":\"https://content.guardianapis.com/travel/2017/feb/26/great-little-ski-resorts-vaujany-france-alps\",\"fields\":{\"headline\":\"Great little ski resorts: Vaujany, France\",\"standfirst\":\"<p>This unspoilt village on the doorstep of the vast Alpe d’Huez ski area offers stunning scenery, but is free of the crowds and commercialism of the bigger resort</p>\",\"trailText\":\"This unspoilt village on the doorstep of the vast Alpe d’Huez ski area offers stunning scenery, but is free of the crowds and commercialism of the bigger resort\",\"byline\":\"Felice Hardy\",\"main\":\"<figure class=\\\"element element-image element--showcase\\\" data-media-id=\\\"a205c3062fb5106bb2f91f24692465b50b049318\\\"> <img src=\\\"https://media.guim.co.uk/a205c3062fb5106bb2f91f24692465b50b049318/0_250_5184_3110/1000.jpg\\\" alt=\\\"Vaujany, France\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> <figcaption> <span class=\\\"element-image__caption\\\">Up and away from it all … Vaujany, France</span> </figcaption> </figure>\",\"body\":\"<h2><strong>Er, where?</strong></h2> <p>The village of Vaujany, an hour by car from Grenoble, struck unexpected gold in the 1980s when the government compulsorily purchased a giant chunk of land for France’s largest hydro-electric scheme. Astute villagers invested the money in what was then the largest cable-car in the country, to reach the slopes above the neighbouring Alpe d’Huez ski resort.</p> <h2><strong>Why try it?</strong></h2> <p>The vast size of the ski area, gobsmacking scenery and the family facilities, including a <a href=\\\"http://www.vaujany.com/en/the-nursery-childrens-club\\\">state-of-the-art creche</a> and leisure complex. Vaujany is an unspoilt destination offering great skiing without the big resort hubbub. It won’t suit partygoers and keen shoppers though.</p> <h2><strong>Mountain lowdown</strong></h2>  <figure class=\\\"element element-image\\\" data-media-id=\\\"b9073ef668bcfce5954ac9cd60429432af4ad2e3\\\"> <img src=\\\"https://media.guim.co.uk/b9073ef668bcfce5954ac9cd60429432af4ad2e3/0_173_5184_3110/1000.jpg\\\" alt=\\\"Vaujany, France\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> </figure>  <p>In its own ski area at Montfrais, above Vaujany, is a nursery slope served by a covered magic-carpet lift, particularly good for children. A gentle bowl over in Alpe d’Huez is arguably the best beginner area in the Alps. Intermediates can ski from village to village on wide-open slopes. For expert skiers, the 16km Sarenne black run is not to be missed, and there’s some fabulous off-piste terrain, both above Vaujany itself and around Alpe d’Huez. Don’t miss La Combe du Loup (Valley of the Wolf) with its views across to La Grave and the mighty La Meije mountain. The first part is steep, and it often has excellent powder. In the natural half-pipes and undulating terrain here you might not see another soul.<br /></p> <h2><strong>Off the hill?</strong><br /></h2> <p>The village has a few small shops, one supermarket, a 1,000-year-old church and lots of ancient barns and farmhouses. The <a href=\\\"http://www.vaujany.com/en/the-leisure-center\\\">focal point for families is the leisure centre</a>, with swimming pool and ice-skating rink, as well as the creche and a restaurant. There’s also a toboggan run, zorbing and visits to an ice cave.</p> <h2><strong>Après any good?</strong></h2> <p>This is not a party resort: the few bars are all pretty quiet. Neighbouring Alpe d’Huez is the place for après action, but <a href=\\\"http://www.lafoliedouce.com/en/section-cuisine/82-la-fruitiere-en.html\\\">La Folie Douce</a>, at the top of the Marmotte chair lift has live music and entertainment on its terrace.</p> <h2><strong>Where do I eat?</strong></h2>  <figure class=\\\"element element-image\\\" data-media-id=\\\"73934d29dd0574aaad7db3592506c86fd92e8b90\\\"> <img src=\\\"https://media.guim.co.uk/73934d29dd0574aaad7db3592506c86fd92e8b90/102_0_3060_1836/1000.jpg\\\" alt=\\\"A dish at Chalet Saskia. Vaujany, France.\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> <figcaption> <span class=\\\"element-image__caption\\\">A dish at Chalet Saskia.</span> </figcaption> </figure>  <p>Some of the best food in the Alps is at <a href=\\\"http://www.skipeak.net/our-accommodation/chalet-saskia\\\">Chalet Saskia</a>, where Kiwi chef Marcus Cull has worked for 20 years: expect lots of tiny courses, with a host of creative amuse-bouches. Otherwise there’s <a href=\\\"http://www.restaurant-laremise.com/index.php?langue=en\\\">La Remise</a> (wood-fired pizzas, old farming implements hanging from the ceiling) in the village. Up on the slopes, there are some excellent lunch places. Try cosy <a href=\\\"https://www.facebook.com/chaletbesson/\\\">Chalet du Lac Besson</a> on the cross-country track (regional dishes beside a log fire) or L’Altibar, next to the altiport, where you can eat while watching microlights take off. <a href=\\\"https://www.facebook.com/Auberge-de-la-combe-haute-160837390615293/\\\">La Combe Haute</a>, near the end of the Sarenne run, is a traditional mountain eatery, with tartiflette, pizzas and salads at good prices. Then there’s the Folie Douce bar/self-service with its adjacent gourmet restaurant <a href=\\\"http://www.lafoliedouce.com/en/section-cuisine/82-la-fruitiere-en.html\\\">La Fruitière</a>.</p> <h2><strong>Where do I stay?</strong></h2>  <figure class=\\\"element element-image\\\" data-media-id=\\\"bc83e8f3e18d2dad20c44b9fc76419cad71ae253\\\"> <img src=\\\"https://media.guim.co.uk/bc83e8f3e18d2dad20c44b9fc76419cad71ae253/0_358_3992_2396/1000.jpg\\\" alt=\\\"Exterior of Chalet Saskia, Vaujany, France.\\\" width=\\\"1000\\\" height=\\\"600\\\" class=\\\"gu-image\\\" /> <figcaption> <span class=\\\"element-image__caption\\\">Chalet Saskia.</span> </figcaption> </figure>  <p>Chalet Saskia is the most comfortable place to stay. It’s an attractive chalet that sleeps 10 in each of its two halves, and is convenient for the main cable car and the leisure centre, though the main reason to choose this chalet is for its great food. The smaller chalets also have excellent food. Chalets Rostaing, Lucette, Gentiane and Dibona are all in the hamlet of La Villette, with a week half-board from £485pp. Higher up in the main village is <a href=\\\"http://www.laperle-location-vaujany.fr/en/\\\">Residence La Perle de l’Oisans</a> , with 11 spacious apartments from €600 a week. Those on tighter budgets could try <a href=\\\"http://www.maeva.com/fr-fr/residence-le-dome-des-rousses_2805.html?duree=7\\\">Résidence Maeva Le Dome des Rousses</a>, with studios for three from €390 a week.</p> <h2><strong>The nitty gritty</strong></h2> <p>248km of pistes in the shared Alpe d’Huez ski area; 84 lifts; the highest altitude is 3,330m</p> <p><strong>Good for</strong> Scenery, unspoilt village atmosphere, fabulous mountain restaurants, off-piste opportunities, families</p> <p><strong>Limitations</strong> Vaujany is low-lying so you may not be able to ski back down to the village in December or mid-April. It’s very quiet, with few bars and limited nightlife</p> <p><strong>Getting there</strong> Fly to Grenoble or Chambéry, both 90 minutes away</p> <p><strong>Costs</strong> Lift pass €157-215 a week. Beer €2-€2.50 a demi or €4-5 a pint</p> <p>• <strong>Green runs </strong>38 | • <strong>Blue runs </strong>32 | • <strong>Red runs </strong>34 | • <strong>Black runs </strong>16</p>\",\"newspaperPageNumber\":\"6\",\"wordcount\":\"710\",\"commentCloseDate\":\"2017-03-01T10:00:27Z\",\"commentable\":\"true\",\"firstPublicationDate\":\"2017-02-26T10:00:27Z\",\"isInappropriateForSponsorship\":\"false\",\"isPremoderated\":\"false\",\"lastModified\":\"2017-03-13T16:21:45Z\",\"newspaperEditionDate\":\"2017-02-25T00:00:00Z\",\"productionOffice\":\"UK\",\"publication\":\"The Guardian\",\"shortUrl\":\"https://gu.com/p/6xzfy\",\"shouldHideAdverts\":\"false\",\"showInRelatedContent\":\"true\",\"thumbnail\":\"https://media.guim.co.uk/a205c3062fb5106bb2f91f24692465b50b049318/0_250_5184_3110/500.jpg\",\"legallySensitive\":\"false\",\"lang\":\"en\",\"bodyText\":\"Er, where? The village of Vaujany, an hour by car from Grenoble, struck unexpected gold in the 1980s when the government compulsorily purchased a giant chunk of land for France’s largest hydro-electric scheme. Astute villagers invested the money in what was then the largest cable-car in the country, to reach the slopes above the neighbouring Alpe d’Huez ski resort. Why try it? The vast size of the ski area, gobsmacking scenery and the family facilities, including a state-of-the-art creche and leisure complex. Vaujany is an unspoilt destination offering great skiing without the big resort hubbub. It won’t suit partygoers and keen shoppers though. Mountain lowdown In its own ski area at Montfrais, above Vaujany, is a nursery slope served by a covered magic-carpet lift, particularly good for children. A gentle bowl over in Alpe d’Huez is arguably the best beginner area in the Alps. Intermediates can ski from village to village on wide-open slopes. For expert skiers, the 16km Sarenne black run is not to be missed, and there’s some fabulous off-piste terrain, both above Vaujany itself and around Alpe d’Huez. Don’t miss La Combe du Loup (Valley of the Wolf) with its views across to La Grave and the mighty La Meije mountain. The first part is steep, and it often has excellent powder. In the natural half-pipes and undulating terrain here you might not see another soul. Off the hill? The village has a few small shops, one supermarket, a 1,000-year-old church and lots of ancient barns and farmhouses. The focal point for families is the leisure centre, with swimming pool and ice-skating rink, as well as the creche and a restaurant. There’s also a toboggan run, zorbing and visits to an ice cave. Après any good? This is not a party resort: the few bars are all pretty quiet. Neighbouring Alpe d’Huez is the place for après action, but La Folie Douce, at the top of the Marmotte chair lift has live music and entertainment on its terrace. Where do I eat? Some of the best food in the Alps is at Chalet Saskia, where Kiwi chef Marcus Cull has worked for 20 years: expect lots of tiny courses, with a host of creative amuse-bouches. Otherwise there’s La Remise (wood-fired pizzas, old farming implements hanging from the ceiling) in the village. Up on the slopes, there are some excellent lunch places. Try cosy Chalet du Lac Besson on the cross-country track (regional dishes beside a log fire) or L’Altibar, next to the altiport, where you can eat while watching microlights take off. La Combe Haute, near the end of the Sarenne run, is a traditional mountain eatery, with tartiflette, pizzas and salads at good prices. Then there’s the Folie Douce bar/self-service with its adjacent gourmet restaurant La Fruitière. Where do I stay? Chalet Saskia is the most comfortable place to stay. It’s an attractive chalet that sleeps 10 in each of its two halves, and is convenient for the main cable car and the leisure centre, though the main reason to choose this chalet is for its great food. The smaller chalets also have excellent food. Chalets Rostaing, Lucette, Gentiane and Dibona are all in the hamlet of La Villette, with a week half-board from £485pp. Higher up in the main village is Residence La Perle de l’Oisans , with 11 spacious apartments from €600 a week. Those on tighter budgets could try Résidence Maeva Le Dome des Rousses, with studios for three from €390 a week. The nitty gritty 248km of pistes in the shared Alpe d’Huez ski area; 84 lifts; the highest altitude is 3,330m Good for Scenery, unspoilt village atmosphere, fabulous mountain restaurants, off-piste opportunities, families Limitations Vaujany is low-lying so you may not be able to ski back down to the village in December or mid-April. It’s very quiet, with few bars and limited nightlife Getting there Fly to Grenoble or Chambéry, both 90 minutes away Costs Lift pass €157-215 a week. Beer €2-€2.50 a demi or €4-5 a pint • Green runs 38 | • Blue runs 32 | • Red runs 34 | • Black runs 16\",\"charCount\":\"3984\"},\"isHosted\":false}]}}";

    public List<Article> getListOfArticle(String url) {

        if (TextUtils.isEmpty(url)) {
            return null;
        }

        URL urlArticles = null;
        try {
            urlArticles = new URL(url);

        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        String JSON = createJSONFromUrl(urlArticles);


        List<Article> list = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(JSON);
            root = root.getJSONObject("response");
            JSONArray results = root.getJSONArray("results");

            int size = results.length();
            JSONObject temp;
            JSONObject temp2;
            for (int i = 0; i < size; i++) {
                temp = results.getJSONObject(i);
                Article article = new Article();


                article.setTitle(temp.getString("webTitle"));
                article.setUrl(temp.getString("webUrl"));
                temp2 = temp.getJSONObject("fields");
                article.setQuickInfo(temp2.getString("trailText"));
                //article.setThumnailUrl(temp2.getString("thumbnail"));
                Log.e("date " + i, temp.getString("webPublicationDate"));

                //Date date  = new Date();
                list.add(article);
            }

            Log.e("&&", list.toString());
            return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

    private String createJSONFromUrl(URL url) {

        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setInstanceFollowRedirects(true);
            urlConnection.connect();

            //if it was successful response code 200
            // the read the input steam
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);

            } else {
                Log.e(LOG_TAG, "STATUS CODE: " + urlConnection.getResponseCode());
            }

            return jsonResponse;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e(LOG_TAG, jsonResponse);

        return jsonResponse;
    }


    private String readFromStream(InputStream inputStream) throws IOException {

        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();


    }
}
