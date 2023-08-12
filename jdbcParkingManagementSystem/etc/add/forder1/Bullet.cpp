#include "pch.h"
#include "Bullet.h"


Bullet::Bullet(COORD& startPos)
{
	pos = startPos;
	image = "!";
	isAlive = 1;
}


Bullet::~Bullet()
{
}

void Bullet::tick() {
	move(COORD{ 0,-1 });
	if (pos.Y <= 2) { // ������ ���� ���������
		die();
	}
}
