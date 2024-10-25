function singleRoll(dice, bonus) {
	let result = Math.floor(Math.random() * dice) + 1;
	addBonus(bonus, result);
	totalResult(bonus, result);
	return result;
}

function multiRoll(dice, n, bonus) {
	var arr = [];
	for (var i = 0; i < n; i++) {
		arr.push(singleRoll(dice));
	}
	let result = arr;
	addBonus(bonus, result.join("⦁"));
	let sumResult = result.reduce((acc, curr) => acc + curr);
	totalResult(bonus, sumResult);
	return result;
}

function advRoll(bonus, advantage, disadvantage) {
	let result = multiRoll(20, 2, bonus);
	
	if (advantage) {
		result = Math.max(result[0], result[1]);
	} else if (disadvantage) {
		result = Math.min(result[0], result[1]);
	}
	
	totalResult(bonus, result);
}

function addBonus(bonus, result) {
	if (bonus < 1) {
		$("#resultBox").html(result);
	} else {
		$("#resultBox").html(`${result} (+${bonus})`);
	}
}

function totalResult(bonus, result) {
	let total = result + bonus;
	setTimeout(()=>$("#totalBox").html(total), 200);
}

function roller() {
	var n = Number($("#quant").val());
	var dice = Number($("#faces").val());
	var bonus = Number($("#bonus").val());
	var adv = $("#advantage:checked").val();
	var disadv = $("#disadvantage:checked").val();
	
	//jogada com vantagem/disvantagem
	if (adv || disadv) {
		advRoll(bonus, adv, disadv);
		//jogando apenas 1 dado
	} else if (n < 2) {
		singleRoll(dice, bonus);
		//jogando 2 ou mais dados
	} else {
		multiRoll(dice, n, bonus);
	}
}

$("#advantage").on("click", function() {
	$(".quant").toggle("slow");
	$(".faces").toggle("slow");
	$("#disadvantage").toggle();
});

$("#disadvantage").on("click", function() {
	$(".quant").toggle("slow");
	$(".faces").toggle("slow");
	$("#advantage").toggle();
});

$("#button").mouseover(function (){
	$(".fa-refresh").addClass("fa-spin");
});
$("#button").mouseout(function (){
	$(".fa-refresh").removeClass("fa-spin");
});

$("#button").on("click", function() {
	roller();
});