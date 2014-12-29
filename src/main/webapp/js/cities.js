var allpolygons = [];
var map;

function initialize() {
	var mapOptions = {
		zoom : 12,
		maxZoom : 13,
		minZoon : 10,
		center : new google.maps.LatLng(28.4567692, 77.0524406),
		mapTypeId : google.maps.MapTypeId.ROADMAP,
		scrollwheel : false
	};

	map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

	$.each(allpolygons, function(i, polygon) {
		bermudaTriangle = new google.maps.Polygon({
			paths : polygon,
			strokeColor : '#FFFFFF',
			geodesic : true,
			strokeOpacity : 1.0,
			strokeWeight : 2,
			fillColor : '#FF0000',
			fillOpacity : 0.35
		});

		bermudaTriangle.setMap(map);

	});

	var allowedBounds = new google.maps.LatLngBounds(new google.maps.LatLng(
			28.0, 77.0), new google.maps.LatLng(29.0, 78.0));
	var lastValidCenter = map.getCenter();

	google.maps.event.addListener(map, 'center_changed', function() {
		if (allowedBounds.contains(map.getCenter())) {
			// still within valid bounds, so save the last valid position
			lastValidCenter = map.getCenter();
			return;
		}

		// not valid anymore => return to last valid position
		map.panTo(lastValidCenter);
	});

	var styles = [ {
		stylers : [ {
			hue : "#40e0d0"
		}, {
			saturation : -100
		}, {
			lightness : 30
		} ]
	}, {
		featureType : "road",
		elementType : "geometry",
		stylers : [ {
			lightness : 10
		}, {
			visibility : "simplified"
		} ]
	}, {
		featureType : "road",
		elementType : "labels",
		stylers : [ {
			visibility : "off"
		} ]
	}, {
		featureType : "terrain",
		elementType : "labels",
		stylers : [ {
			visibility : "on"
		}, {
			hue : "#FFFFFF"
		} ]
	} ];

	map.setOptions({
		styles : styles
	});

}

google.maps.event.addDomListener(window, 'load', getData);

function getData() {
	var ids = [];
	// Ids for Gurgaon
	ids.push(277567);
	ids.push(5054621);
	ids.push(26349089);
	ids.push(20672232);
	ids.push(25775893);
	ids.push(4976497);
	ids.push(16543188);
	ids.push(25441659);
	ids.push(4879684);
	ids.push(5041078);
	ids.push(5037615);
	ids.push(5041106);
	ids.push(5041026);
	ids.push(13625188);
	ids.push(31771633);
	ids.push(18267451);
	ids.push(18267431);
	ids.push(27806844);
	ids.push(18398887);
	ids.push(17468948);
	ids.push(6302093);
	ids.push(11454201);
	ids.push(11454473);
	ids.push(13352859);
	ids.push(4880237);
	ids.push(4859050);
	ids.push(161481);
	ids.push(4811971);
	ids.push(4812088);
	ids.push(945285);
	ids.push(29209090);
	ids.push(4976435);
	ids.push(4811939);
	ids.push(5011895);
	ids.push(25401679);
	ids.push(5011613);
	ids.push(5011515);
	ids.push(5011320);
	ids.push(21719215);
	ids.push(21719126);
	ids.push(21417315);
	ids.push(5021369);
	ids.push(24204797);
	ids.push(4879725);
	ids.push(16806642);
	ids.push(25702858);
	ids.push(13785435);
	ids.push(20560780);
	ids.push(10816046);
	ids.push(27628912);
	ids.push(25357458);
	ids.push(18398855);
	ids.push(18398788);
	ids.push(19294996);
	ids.push(18398779);
	ids.push(18398741);
	ids.push(16190470);
	ids.push(29215926);
	ids.push(18398763);
	ids.push(18267349);
	ids.push(18398855);
	ids.push(5618526);
	ids.push(4859165);
	ids.push(4859271);
	ids.push(4865380);
	ids.push(4865510);
	ids.push(4811758);
	ids.push(4812088);
	ids.push(4811971);
	ids.push(4880634);
	ids.push(27664360);
	ids.push(11454686);
	ids.push(11454889);
	ids.push(11454669);
	ids.push(11454630);
	ids.push(18909453);
	ids.push(25357458);
	ids.push(357646);
	ids.push(4880141);
	ids.push(4880211);
	ids.push(4880237);
	ids.push(945285);
	ids.push(17391856);
	ids.push(4976586);
	ids.push(4881022);
	ids.push(24710891);
	ids.push(4881176);
	ids.push(11454769);
	ids.push(4604774);
	ids.push(27806844);
	ids.push(25441343);
	ids.push(18939724);
	ids.push(25441369);
	ids.push(19467523);
	ids.push(5011895);
	ids.push(197775);
	ids.push(1806699);
	ids.push(29259314);
	ids.push(25441357);
	ids.push(25701783);
	ids.push(5331246);
	ids.push(5011936);
	ids.push(19724311);
	ids.push(14849259);
	ids.push(4879987);
	ids.push(26349074);
	ids.push(25441388);
	ids.push(4976688);
	ids.push(4976283);
	ids.push(16166220);
	ids.push(15250937);
	ids.push(4879617);
	ids.push(4813210);
	ids.push(20118818);
	ids.push(4813156);
	ids.push(4879891);
	ids.push(4880416);
	ids.push(15103029);
	ids.push(4879783);
	ids.push(25441670);
	ids.push(4880761);
	ids.push(25441688);
	ids.push(4859228);
	ids.push(22896257);
	ids.push(4865380);
	ids.push(5392762);
	ids.push(4865380);
	ids.push(18249568);
	ids.push(25701754);
	ids.push(25701692);
	ids.push(5843707);
	ids.push(25701692);
	ids.push(25701719);
	ids.push(21773236);
	ids.push(470049);
	ids.push(25701675);
	ids.push(27806608);
	ids.push(25641582);
	ids.push(21567710);
	ids.push(24247857);
	ids.push(30516686);
	ids.push(26955401);
	ids.push(4811674);
	ids.push(18315075);
	ids.push(3107159);
	ids.push(5021428);
	ids.push(5021261);
	ids.push(4859114);
	ids.push(27504746);
	ids.push(9282666);
	ids.push(23737810);
	ids.push(4813129);
	ids.push(21085952);

	var ids1 = [];
	$.each(ids, function(id, loc) {
		$.ajax({
			url : "Data/gurgaon/" + loc,
			success : function(data) {
				var triangleCoords = [];
				$.each(data.polygon, function(c, s) {
					triangleCoords.push(new google.maps.LatLng(s.y, s.x));
				});
				allpolygons.push(triangleCoords);
			},
			async : false,
			type : "GET",
			dataType : 'json'
		});
	});
	initialize();
}
