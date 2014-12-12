
function initStates() {
  var mapOptions = {
    zoom: 5,
    maxZoom:13,
    center: new google.maps.LatLng(23.29004039,78.44355469),
    mapTypeId: google.maps.MapTypeId.ROAD,
    scrollwheel: false,
    zoomable:false
  };


var map = new google.maps.Map(document.getElementById('map-india-canvas'),  mapOptions);
/*var cities = [
              "karnataka","haryana","jammuandkashmir","punjab","himachalpradesh","rajasthan","kerala","maharashtra","tamilnadu","andhrapradesh","uttarakand",
              "uttarpradesh","delhi","madhyapradesh","bihar","jharkhand","chhattisgarh","odhisa","gujarat","telangana","sikkim",
              "assam","nagaland","tripura","mizoram","goa","westbengal","arunachalpradesh","manipur","meghalaya"
              ];
*/
var cities = ["maharashtra"];

var arrayLength = cities.length;
//var colors = ['#eea339','#4ba24b','#18bc9c','#eea339','#18bc9c','#f69d9d']
    var colors = ['#cc3637','#f15854','#18bc9c','#f15854','#18bc9c','#eea339']
//var colors = ['#cc3637','#f15854','#5da5da','#ff7148','#ec583a','#DE1B1B']

for (var i = 0; i < arrayLength; i++) {
 var coords = [];

  $.ajax({ url:"Data/cities/"+cities[i], success:function( data ) {
                      $.each(data, function(c,s) {
                          coords.push(new google.maps.LatLng(s.lat, s.lon));
                      });
            }, async:false , type:"GET", dataType: 'json'});

  currColor = colors[Math.floor(Math.random() * 5) + 1];
  bermudaTriangle = new google.maps.Polygon({
      paths: coords,
      strokeColor: "white",
      strokeOpacity: 1.5,
      strokeWeight: 1,
      fillColor: currColor,
      fillOpacity: 1.0
    });
	
	
	$.each(coords, function(i , x){
		

			 var marker = new google.maps.Marker({
				position: x,
				map: map,
				title: 'Hello'+ x.k + x.B,
				draggable:true
			    });
			google.maps.event.addListener(marker, "click", function (event) {
                   		 console.log(this.position);
			}); 

	});



/*google.maps.event.addListener(bermudaTriangle,"mouseover",function(){
 this.setOptions({fillColor: "#4ba24b"});
}); 

google.maps.event.addListener(bermudaTriangle,"mouseout",function(){
 this.setOptions({fillColor: currColor});
});

  bermudaTriangle.setMap(map);*/
}

styles=[{
    "featureType": "water",
    "elementType": "geometry",
    "stylers": [
      { visibility: "on" },
      { hue: "#1b66d8" },
      { saturation: 100 },
      { lightness: -10 },
    ]
}, {
    "featureType": "landscape",
    "elementType": "geometry",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 20
    }]
}, {
    "featureType": "road.highway",
    "elementType": "geometry.fill",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 17
    }]
}, {
    "featureType": "road.highway",
    "elementType": "geometry.stroke",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 29
    }, {
        "weight": 0.2
    },{
        "visibility": "off"
    }]
}, {
    "featureType": "road.arterial",
    "elementType": "geometry",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 18
    },{
        "visibility": "off"
    }]
}, {
    "featureType": "road.local",
    "elementType": "geometry",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 16
    },{
        "visibility": "off"
    }]
}, {
    "featureType": "poi",
    "elementType": "geometry",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 21
    }]
}, {
    "elementType": "labels.text.stroke",
    "stylers": [{
        "visibility": "off"
    }, {
        "color": "#000000"
    }, {
        "lightness": 16
    }]
}, {
    "elementType": "labels.text.fill",
    "stylers": [{
        "saturation": 36
    }, {
        "color": "#000000"
    }, {
        "lightness": 40
    }, {
        "visibility": "off"
    }]
}, {
    "elementType": "labels.icon",
    "stylers": [{
        "visibility": "off"
    }]
}, {
    "featureType": "transit",
    "elementType": "geometry",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 19
    }]
}, {
    "featureType": "administrative",
    "elementType": "geometry.fill",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 20
    }]
}, {
    "featureType": "administrative",
    "elementType": "geometry.stroke",
    "stylers": [{
        "color": "#000000"
    }, {
        "lightness": 17
    }, {
        "weight": 1.2
    }]
}]

/*map.setOptions({styles: styles});*/
}

google.maps.event.addDomListener(window, 'load', initStates);

